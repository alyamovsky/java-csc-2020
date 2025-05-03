import edu.TestCase;
import edu.lesson1.syntax.OverflowException;
import edu.lesson1.syntax.PrimitiveToolbox;
import edu.lesson2.objects.Car;
import edu.lesson2.objects.Lesson2;
import edu.lesson2.objects.Point;
import edu.lesson2.objects.named.Group;
import edu.lesson2.objects.named.Named;
import edu.lesson2.objects.named.User;

void main() {
    lesson1();
    lesson2();

    System.out.println(TestCase.getStats());
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

    TestCase.assertEquals("Hello, world! 😃".length(), 16);
    TestCase.assertEquals(toolbox.codePoints("Hello, world! 😃").length, 15);
    TestCase.assertEquals(toolbox.isPalindrome("Hello, world! 😃"), false);
    TestCase.assertEquals(toolbox.isPalindrome("wow 😃 wow"), true);
}

private static void lesson2() {
    TestCase.assertEquals(3 + 4 * 5 >> 1 & ~2 + 1, 10);

    var lesson2 = new Lesson2();
    TestCase.assertEquals(lesson2.route(1), "CREATE");
    TestCase.assertEquals(lesson2.route(3), "UPDATE");
    TestCase.assertEquals(lesson2.route(5), "DELETE");
    TestCase.assertEquals(lesson2.route(10), "UNKNOWN(10)");

    TestCase.assertEquals(new Point(2, 3).getX(), 2);
    TestCase.assertEquals(new Point(2, 3).getY(), 3);
    var point1 = new Point(2, 3);
    var point2 = point1.withY(3);
    TestCase.assertEquals(point1.equals(point2), true);
    TestCase.assertEquals(point1 == point2, false);

    lesson2.process((int) Instant.now().getEpochSecond(), "Test");

    Car car = Car.builder().length(5.5).color("brown").build();
    TestCase.assertEquals(car.length, 5.5);
    TestCase.assertEquals(car.color, "brown");

    Named user = new User("John");
    user.print();
    var group = new Group("support");
    group.print();
}