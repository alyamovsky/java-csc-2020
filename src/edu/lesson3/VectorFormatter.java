package edu.lesson3;

public interface VectorFormatter {
    // what is the correct way of using static interface methods?
    static String bytesToHuman(ImmutableVector v, CoordinateOrder order) {
        return VectorFormatterBitwise.bytesToHuman(v, order);
    }

    enum CoordinateOrder {
        XYZ,
        ZYX,
        X_ONLY,
    }
}
