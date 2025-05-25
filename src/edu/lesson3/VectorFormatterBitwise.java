package edu.lesson3;

public class VectorFormatterBitwise implements VectorFormatter {
    public static String bytesToHuman(ImmutableVector v, CoordinateOrder order) {
        var x = dumpHex(Float.floatToIntBits((float) v.getX()));
        var y = dumpHex(Float.floatToIntBits((float) v.getY()));
        var z = dumpHex(Float.floatToIntBits((float) v.getZ()));

        return switch (order) {
            case XYZ -> x + " " + y + " " + z;
            case ZYX -> z + " " + y + " " + x;
            case X_ONLY -> x;
        };
    }

    private static String dumpHex(int v) {
        var sb = new StringBuilder();
        for (var i = 3; i >= 0; i--) {
            var b = v >> (i * 8) & 0xFF;
            sb.append(String.format("%02X-", b));
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
