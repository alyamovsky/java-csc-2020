package edu;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiPredicate;

public class TestCase {
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private static final String COLOR_BRIGHT_WHITE = "\u001B[97m";
    private static final String BG_DARK_RED = "\u001B[48;5;88m";
    private static final String BG_DARK_GREEN = "\u001B[48;5;22m";
    private static int passedTests = 0;
    private static int failedTests = 0;

    private TestCase() {
    }

    public static <T> void assertException(Class<? extends Throwable> exceptionClass, Callable<T> function) {
        try {
            function.call();
        } catch (Exception exception) {
            if (exceptionClass.isInstance(exception)) {
                TestCase.passedTests++;
            } else {
                TestCase.failedTests++;
                System.out.println("Assert exception error:");
                System.out.println("Actual: " + exception.getClass());
                System.out.println("Expected: " + exceptionClass);
            }

            return;
        }

        throw new AssertionError("Exception has not been thrown");
    }

    public static String getStats() {
        String prefix;
        String bgColor;
        if (TestCase.failedTests > 0) {
            bgColor = BG_DARK_RED + COLOR_BRIGHT_WHITE;
            prefix = bgColor + "Some tests are failing" + COLOR_RESET;
        } else {
            bgColor = BG_DARK_GREEN + COLOR_BRIGHT_WHITE;
            prefix = bgColor + "All tests are passed" + COLOR_RESET;
        }
        return prefix + "\n" + bgColor + "Passed tests: " + TestCase.passedTests + ", failed tests: " + TestCase.failedTests +
                COLOR_RESET;
    }

    public static <T> void assertEquals(T expected, T actual) {
        doAssert(Object::equals, expected, actual);
    }

    public static <T> void assertSame(T expected, T actual) {
        doAssert((a, b) -> a == b, expected, actual);
    }

    public static void assertTrue(boolean condition) {
        doAssert(Objects::equals, condition, true);
    }

    private static <T> void doAssert(BiPredicate<T, T> function, T expected, T actual) {
        if (function.test(expected, actual)) {
            TestCase.passedTests++;
        } else {
            TestCase.failedTests++;
            var stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 3) {
                StackTraceElement caller = stackTrace[3];
                System.out.println(
                         COLOR_RED + "Assertion failed at " + caller.getClassName() + "." + caller.getMethodName() + "() (" +
                                caller.getFileName() + ":" + caller.getLineNumber() + ")" + COLOR_RESET);
            }

            System.out.println(COLOR_RED + "Expected: (" + expected.getClass() + ") " + expected + COLOR_RESET);
            System.out.println(COLOR_RED + "Actual: (" + actual.getClass() + ") " + actual + COLOR_RESET);
        }
    }
}
