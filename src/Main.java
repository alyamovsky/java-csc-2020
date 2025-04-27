import edu.lesson1.syntax.OverflowException;
import edu.lesson1.syntax.PrimitiveToolbox;

void main() {
    var toolbox = new PrimitiveToolbox();

    TestCase.assertEquals(toolbox.parseStringLiteral("5"), 5);
    TestCase.assertEquals(toolbox.parseStringLiteral("55"), 55);
    TestCase.assertEquals(toolbox.parseStringLiteral("+55"), 55);
    TestCase.assertEquals(toolbox.parseStringLiteral("-55"), -55);
    TestCase.assertEquals(toolbox.parseStringLiteral("55_5"), 555);
    TestCase.assertEquals(toolbox.parseStringLiteral("0b101"), 5);
    TestCase.assertEquals(toolbox.parseStringLiteral("-0b101"), -5);
    TestCase.assertEquals(toolbox.parseStringLiteral("-0b1_0_1"), -5);
    TestCase.assertEquals(toolbox.parseStringLiteral("0x64"), 100);
    TestCase.assertEquals(toolbox.parseStringLiteral("0777"), 511);

    TestCase.assertException(OverflowException.class, () -> toolbox.safeAdd(Long.MAX_VALUE, 1));
}

static class TestCase {
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
        } catch (Throwable exception) {
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
