package edu.lesson4.vector;

import java.util.Objects;
import java.util.random.RandomGenerator;

public class ImmutableVector {
    private static final RandomGenerator RNG = RandomGenerator.getDefault();

    private static final ImmutableVector ZERO = new ImmutableVector(0, 0, 0);
    private static final ImmutableVector UNIT_X = new ImmutableVector(1, 0, 0);
    private static final ImmutableVector UNIT_Y = new ImmutableVector(0, 1, 0);
    private static final ImmutableVector UNIT_Z = new ImmutableVector(0, 0, 1);
    private final double x;
    private final double y;
    private final double z;

    private ImmutableVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static ImmutableVector of(double x, double y, double z) {
        if (x == 0 && y == 0 && z == 0) {
            return ZERO;
        }
        if (x == 1 && y == 0 && z == 0) {
            return UNIT_X;
        }
        if (x == 0 && y == 1 && z == 0) {
            return UNIT_Y;
        }
        if (x == 0 && y == 0 && z == 1) {
            return UNIT_Z;
        }

        return new ImmutableVector(x, y, z);
    }

    public static ImmutableVector random() {
        return new ImmutableVector(
            RNG.nextDouble(-Integer.MAX_VALUE, Integer.MAX_VALUE),
            RNG.nextDouble(-Integer.MAX_VALUE, Integer.MAX_VALUE),
            RNG.nextDouble(-Integer.MAX_VALUE, Integer.MAX_VALUE)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImmutableVector that)) {
            return false;
        }
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0 && Double.compare(z, that.z) == 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f, %.3f)", x, y, z);
    }

    public int length() {
        return this.toString().length();
    }
}
