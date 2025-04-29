package edu;

import java.util.concurrent.Callable;

public class TestCase {
    public static <T> void assertEquals(T actual, T expected) {
        try {
            assert actual.equals(expected);
        } catch (AssertionError exception) {
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
            } catch (AssertionError assertException) {
                System.out.println("Assert exception error:");
                System.out.println("Actual: " + exception.getClass());
                System.out.println("Expected: " + exceptionClass);
            }

            return;
        }

        throw new AssertionError("Exception has not been thrown");
    }
}
