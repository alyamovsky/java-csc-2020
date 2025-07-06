package edu.lesson3.vector;

import java.nio.ByteBuffer;

public class VectorFormatterByteBuffer implements VectorFormatter {
    public static String bytesToHuman(ImmutableVector v, CoordinateOrder order) {
        var x = dumpHex((float) v.getX());
        var y = dumpHex((float) v.getY());
        var z = dumpHex((float) v.getZ());

        return switch (order) {
            case XYZ -> x + " " + y + " " + z;
            case ZYX -> z + " " + y + " " + x;
            case X_ONLY -> x;
        };
    }

    private static String dumpHex(float v) {
        var buffer = ByteBuffer.allocate(4);
        buffer.putFloat(v);
        var sb = new StringBuilder();
        for (byte b : buffer.array()) {
            sb.append(String.format("%02X-", b));
        }

        sb.setLength(sb.length() - 1);

        return sb.toString();
    }
}
