package edu.lesson4.inventory.core.box;

import java.util.function.Function;

public class MutableBox <T> implements Box<T>{
        private T value;

    private MutableBox(T value) {
        this.value = value;
    }

    public static <T> MutableBox<T> of(T value) {
        return new MutableBox<>(value);
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public T get() {
        return value;
    }

    public Box<T> set(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        this.value = value;

        return this;
    }

    @Override
    public T orElse(T value) {
        if (this.value == null) {
            return value;
        }

        return this.value;
    }

    @Override
    public <U> Box<U> flatMap(Function<? super T, ? extends Box<U>> mapper) {
        return mapper.apply(value);
    }

    @Override
    public <U> Box<U> map(Function<? super T, ? extends U> mapper) {
        return ImmutableBox.of(mapper.apply(value));
    }
}
