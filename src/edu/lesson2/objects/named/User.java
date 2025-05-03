package edu.lesson2.objects.named;

public class User implements Named {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
