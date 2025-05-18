import edu.TestCase;
import edu.lesson1.syntax.OverflowException;
import edu.lesson1.syntax.PrimitiveToolbox;
import edu.lesson2.objects.Car;
import edu.lesson2.objects.Lesson2;
import edu.lesson2.objects.Point;
import edu.lesson2.objects.named.Group;
import edu.lesson2.objects.named.Named;
import edu.lesson2.objects.named.User;
import edu.lesson3.Axis;
import edu.lesson3.ImmutableVector;

void main() {
    lesson1();
    lesson2();
    lesson3();

    System.out.println(TestCase.getStats());
}

private static void lesson1() {
    var toolbox = new PrimitiveToolbox();

    TestCase.assertEquals(5, toolbox.parseStringLiteral("5"));
    TestCase.assertEquals(55, toolbox.parseStringLiteral("55"));
    TestCase.assertEquals(55, toolbox.parseStringLiteral("+55"));
    TestCase.assertEquals(-55, toolbox.parseStringLiteral("-55"));
    TestCase.assertEquals(555, toolbox.parseStringLiteral("55_5"));
    TestCase.assertEquals(5, toolbox.parseStringLiteral("0b101"));
    TestCase.assertEquals(-5, toolbox.parseStringLiteral("-0b101"));
    TestCase.assertEquals(-5, toolbox.parseStringLiteral("-0b1_0_1"));
    TestCase.assertEquals(100, toolbox.parseStringLiteral("0x64"));
    TestCase.assertEquals(51966, toolbox.parseStringLiteral("0xCAFE"));
    TestCase.assertEquals(511, toolbox.parseStringLiteral("0777"));

    TestCase.assertException(OverflowException.class, () -> toolbox.safeAdd(Long.MAX_VALUE, 1));

    System.out.println(toolbox.machineEpsilon());

    TestCase.assertEquals(16, "Hello, world! 😃".length());
    TestCase.assertEquals(15, toolbox.codePoints("Hello, world! 😃").length);
    TestCase.assertEquals(false, toolbox.isPalindrome("Hello, world! 😃"));
    TestCase.assertEquals(true, toolbox.isPalindrome("wow 😃 wow"));
}

private static void lesson2() {
    TestCase.assertEquals(10, 3 + 4 * 5 >> 1 & ~2 + 1);

    var lesson2 = new Lesson2();
    TestCase.assertEquals("CREATE", lesson2.route(1));
    TestCase.assertEquals("UPDATE", lesson2.route(3));
    TestCase.assertEquals("DELETE", lesson2.route(5));
    TestCase.assertEquals("UNKNOWN(10)", lesson2.route(10));

    TestCase.assertEquals(2, new Point(2, 3).getX());
    TestCase.assertEquals(3, new Point(2, 3).getY());

    var point1 = new Point(2, 3);
    var point2 = point1.withY(3);
    TestCase.assertEquals(true, point1.equals(point2));
    TestCase.assertEquals(false, point1 == point2);

    lesson2.process((int) Instant.now().getEpochSecond(), "Test");

    Car car = Car.builder().length(5.5).color("brown").build();
    TestCase.assertEquals(5.5, car.length);
    TestCase.assertEquals("brown", car.color);

    Named user = new User("John");
    user.print();
    var group = new Group("support");
    group.print();
}

private static void lesson3() {
    var zeroV1 = ImmutableVector.of(0, 0, 0);
    var zeroV2 = ImmutableVector.of(0, 0, 0);
    TestCase.assertEquals(zeroV1, zeroV2);
    TestCase.assertSame(zeroV1, zeroV2);
    var xV1 = ImmutableVector.of(1, 0, 0);
    var xV2 = ImmutableVector.of(1, 0, 0);
    TestCase.assertEquals(xV1, xV2);
    TestCase.assertSame(xV1, xV2);
    TestCase.assertEquals(xV1.toString(), "(1.000, 0.000, 0.000)");

    TestCase.assertSame(Axis.X, Axis.closest(ImmutableVector.of(3, 5, 7)));
    TestCase.assertSame(Axis.X, Axis.closest(ImmutableVector.of(-3, 5, 7)));
    TestCase.assertSame(Axis.Y, Axis.closest(ImmutableVector.of(7, 5, 7)));
    TestCase.assertSame(Axis.Y, Axis.closest(ImmutableVector.of(7, 5, -7)));
    TestCase.assertSame(Axis.Z, Axis.closest(ImmutableVector.of(17, 15, -7)));
    TestCase.assertSame(Axis.Z, Axis.closest(ImmutableVector.of(17, 15, -7)));
    TestCase.assertSame(Axis.NONE, Axis.closest(ImmutableVector.of(0, 0, 0)));
}
