package edu.lesson4.vector;

public enum Axis implements VectorSupplier {
    X(1, 0, 0),
    Y(0, 1, 0),
    Z(0, 0, 1),
    NONE(0, 0, 0);
    private final ImmutableVector unitVector;

    Axis(double x, double y, double z) {
        this.unitVector = ImmutableVector.of(x, y, z);
    }

    public static Axis closest(ImmutableVector v) {
        var x = Math.abs(v.getX());
        var y = Math.abs(v.getY());
        var z = Math.abs(v.getZ());

        if (x == 0 && y == 0 && z == 0) {
            return NONE;
        }

        if (x <= y && x <= z) {
            return X;
        }
        if (y <= x && y <= z) {
            return Y;
        }

        return Z;
    }

    @Override
    public ImmutableVector toUnitVector() {
        return unitVector;
    }
}
