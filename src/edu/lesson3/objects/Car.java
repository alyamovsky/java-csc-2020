package edu.lesson3.objects;

public class Car {
    public final double length;
    public final String color;

    private Car(double length, String color) {
        this.length = length;
        this.color = color;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The class is declared static because it should not have access to the outer class instance fields.
     */
    public static class Builder {
        private double length;
        private String color;
        private Builder() {
        }

        public Builder length(double length) {
            this.length = length;

            return this;
        }

        public Builder color(String color) {
            this.color = color;

            return this;
        }
        public Car build() {
            return new Car(length, color);
        }
    }
}
