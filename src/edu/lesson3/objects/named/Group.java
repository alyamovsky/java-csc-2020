package edu.lesson3.objects.named;

public class Group implements Named {
    private final String name;

    public Group(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
