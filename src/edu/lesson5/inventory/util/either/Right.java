package edu.lesson5.inventory.util.either;

import java.util.Objects;

public record Right<L, R>(R value) implements Either<L, R> {
    public Right {
        Objects.requireNonNull(value);
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public R right() {
        return value;
    }
}