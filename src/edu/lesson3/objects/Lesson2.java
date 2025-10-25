package edu.lesson3.objects;

public class Lesson2 {
    public String route(int code) {
        return switch (code) {
            case 1 -> "CREATE";
            case 2, 3 -> "UPDATE";
            case 4, 5, 6 -> "DELETE";
            default -> "UNKNOWN(" + code + ")";
        };
    }

    public void process(int time, String message) {
        class Event {
            public final int time;
            public final String message;

            public Event(int time, String message) {
                this.time = time;
                this.message = message;
            }

            @Override
            public String toString() {
                return time + ": " + message;
            }
        }

        var event = new Event(time, message);

        System.out.println(event);
    }
}
