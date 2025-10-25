package edu.lesson5.inventory.core.box;

import java.util.function.Function;

public class ImmutableBox<T> implements Box<T> {
    private final T value;
    private final String label;

    private ImmutableBox(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public static <T> ImmutableBox<T> of(T value) {
        return new ImmutableBox<>(value, "");
    }

    public static <T> ImmutableBox<T> of(T value, String label) {
        return new ImmutableBox<>(value, label);
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public String label() {
        return label;
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
