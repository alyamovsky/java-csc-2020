package edu;

import java.util.concurrent.Callable;

public class TestCase {
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static <T> void assertEquals(T actual, T expected) {
        try {
            assert actual.equals(expected);
            TestCase.passedTests++;
        } catch (AssertionError exception) {
            TestCase.failedTests++;
            var stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length > 2) {
                StackTraceElement caller = stackTrace[2];
                System.out.println(
                        "Assertion failed at " + caller.getClassName() + "." + caller.getMethodName() + "() (" +
                                caller.getFileName() + ":" + caller.getLineNumber() + ")");
            }

            System.out.println("Actual: (" + actual.getClass() + ") " + actual);
            System.out.println("Expected: (" + expected.getClass() + ") " + expected);
        }
    }

    public static <T> void assertException(Class<? extends Throwable> exceptionClass, Callable<T> function) {
        try {
            function.call();
        } catch (Exception exception) {
            try {
                assert exceptionClass.isInstance(exception);
                TestCase.passedTests++;
            } catch (AssertionError assertException) {
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
        return "Passed tests: " + TestCase.passedTests + ", failed tests: " + TestCase.failedTests;
    }
}
