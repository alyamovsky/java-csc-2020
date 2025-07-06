import edu.TestCase;
import edu.lesson1.syntax.OverflowException;
import edu.lesson1.syntax.PrimitiveToolbox;
import edu.lesson2.objects.Car;
import edu.lesson2.objects.Lesson2;
import edu.lesson2.objects.Point;
import edu.lesson2.objects.named.Group;
import edu.lesson2.objects.named.Named;
import edu.lesson2.objects.named.User;
import edu.lesson3.vector.*;

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
    TestCase.assertSame(Axis.Z, Axis.closest(ImmutableVector.of(17, -15, -7)));
    TestCase.assertSame(Axis.Z, Axis.closest(ImmutableVector.of(-17, 15, -7)));
    TestCase.assertSame(Axis.NONE, Axis.closest(ImmutableVector.of(0, 0, 0)));

    var sortV1 = ImmutableVector.of(3, 5, 7);
    var sortV2 = ImmutableVector.of(4, 4, 8);
    var sortV3 = ImmutableVector.of(5, 3, 10);
    var list = List.of(sortV1, sortV2, sortV3);
    var sortedByX = VectorSorter.sortBy(list, VectorSorter.SortCriterion.X);
    TestCase.assertSame(sortV1, sortedByX.getFirst());
    TestCase.assertSame(sortV3, sortedByX.getLast());
    var sortedByY = VectorSorter.sortBy(list, VectorSorter.SortCriterion.Y);
    TestCase.assertSame(sortV3, sortedByY.getFirst());
    TestCase.assertSame(sortV1, sortedByY.getLast());
    var sortedByZ = VectorSorter.sortBy(list, VectorSorter.SortCriterion.Z);
    TestCase.assertSame(sortV1, sortedByZ.getFirst());
    TestCase.assertSame(sortV3, sortedByZ.getLast());

    TestCase.assertEquals(
        "3F-80-00-00 40-00-00-00 40-40-00-00",
        VectorFormatter.bytesToHuman(ImmutableVector.of(1, 2, 3), VectorFormatter.CoordinateOrder.XYZ)
    );
    TestCase.assertEquals(
        "40-40-00-00 40-00-00-00 3F-80-00-00",
        VectorFormatterByteBuffer.bytesToHuman(ImmutableVector.of(1, 2, 3), VectorFormatter.CoordinateOrder.ZYX)
    );
    TestCase.assertEquals(
        "3F-80-00-00",
        VectorFormatterByteBuffer.bytesToHuman(ImmutableVector.of(1, 2, 3), VectorFormatter.CoordinateOrder.X_ONLY)
    );

    TestCase.assertEquals("1.000;0.000;-3.500", VectorStringUtils.toCsvLine(ImmutableVector.of(1, 0, -3.5)));
    TestCase.assertEquals(Optional.of(ImmutableVector.of(1, 0, -3.5)), VectorStringUtils.fromCsvLine("1.000;0.000;-3.500"));
    TestCase.assertEquals(
        """
            1.000;0.000;-3.500
            2.000;1.000;-7.000
            3.000;-15.000;2.500
            """, VectorStringUtils.csvDump(List.of(
                ImmutableVector.of(1, 0, -3.5),
                ImmutableVector.of(2, 1, -7),
                ImmutableVector.of(3, -15, 2.5)
        )));
        TestCase.assertEquals(
        """
            1.000;0.000;-3.500
            2.000;1.000;-7.000
            3.000;-15.000;2.500
            """, VectorStringUtils.csvDumpStream(List.of(
                ImmutableVector.of(1, 0, -3.5),
                ImmutableVector.of(2, 1, -7),
                ImmutableVector.of(3, -15, 2.5)
        )));

    VectorStringUtils.csvDump(
        Stream
            .generate(ImmutableVector::random)
            .limit(1_000)
            .collect(Collectors.toList())
    );

    VectorStringUtils.csvDumpStream(
        Stream
            .generate(ImmutableVector::random)
            .limit(1_000)
            .collect(Collectors.toList())
    );

    System.out.println(
        VectorStringUtils.prettyTable(
            Stream
                .generate(ImmutableVector::random)
                .limit(10)
                .collect(Collectors.toList()
            )
    ));
}

void main(String[] args) {
    if (args.length < 1) {
        throw new RuntimeException("Specify lesson");
    }

    switch (args[0]) {
        case "lesson1", "syntax" -> lesson1();
        case "lesson2", "objects" -> lesson2();
        case "lesson3", "vector" -> lesson3();
        case "all" -> {
            lesson1();
            lesson2();
            lesson3();
        }
    }
    System.out.println(TestCase.getStats());
}
