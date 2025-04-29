import edu.TestCase;
import edu.lesson1.syntax.OverflowException;
import edu.lesson1.syntax.PrimitiveToolbox;

void main() {
    lesson1();
}

private static void lesson1() {
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
    TestCase.assertEquals(toolbox.parseStringLiteral("0xCAFE"), 51966);
    TestCase.assertEquals(toolbox.parseStringLiteral("0777"), 511);

    TestCase.assertException(OverflowException.class, () -> toolbox.safeAdd(Long.MAX_VALUE, 1));

    System.out.println(toolbox.machineEpsilon());

    TestCase.assertEquals(toolbox.codePoints("Hello, world! 😃").length, 15);
    TestCase.assertEquals(toolbox.isPalindrome("Hello, world! 😃"), false);
    TestCase.assertEquals(toolbox.isPalindrome("wow 😃 wow"), true);
}
