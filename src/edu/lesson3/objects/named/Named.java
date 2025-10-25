package edu.lesson3.objects.named;

public interface Named {
    String getName();

    default void print() {
        Printer.print(this);
    }
}
