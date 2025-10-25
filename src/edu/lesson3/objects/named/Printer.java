package edu.lesson3.objects.named;

public class Printer {
    private Printer() {
    }

    public static void print(Named named) {
        System.out.println(named.getName());
    }
}
