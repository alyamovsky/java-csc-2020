package edu.lesson4.inventory.util.either;

import java.util.Objects;

public record Left<L, R>(L value) implements Either<L, R> {
    public Left {
        Objects.requireNonNull(value);
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public L left() {
        return value;
    }
}
