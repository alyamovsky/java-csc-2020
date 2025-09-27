package edu.lesson4.inventory.core.box;

import java.util.function.Function;

public interface Box<T> {
    boolean isPresent();

    <U> Box<U> map(Function<? super T, ? extends U> mapper);

    <U> Box<U> flatMap(Function<? super T, ? extends Box<U>> mapper);

    T get();

    String label();

    T orElse(T value);
}
