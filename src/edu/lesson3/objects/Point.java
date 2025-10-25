package edu.lesson3.objects;

import java.util.Objects;

public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public Point withX(int x) {
        return new Point(x, y);
    }

    public int getY() {
        return y;
    }

    public Point withY(int y) {
        return new Point(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Point p && p.x == x && p.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{\"x\":" + x + ",\"y\":" + y + "}";
    }
}