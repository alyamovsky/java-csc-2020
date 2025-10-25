package edu.lesson5.inventory.ui;

import edu.lesson5.inventory.core.product.ProductFactory;
import edu.lesson5.inventory.core.product.ProductMap;
import edu.lesson5.inventory.core.shipment.Warehouse;

import static java.lang.IO.print;
import static java.lang.IO.println;

public final class Cli {
    private final java.util.Scanner in = new java.util.Scanner(System.in);
    private final Warehouse warehouse = new Warehouse();

    public static void main(String[] args) {
        new Cli().loop();
    }

    private void loop() {
        println("Inventory CLI. Type 'help' for commands.");
        while (true) {
            print("> ");
            var args = in.nextLine().trim().split("\\s+");
            Input input;
            if (args.length >= 3) {
                input = new Input(args[2], Integer.parseInt(args[1]));
            } else {
                input = new Input("none", 0);
            }
            switch (args[0]) {
                case "help" -> help();
                case "list" -> list();
                case "in", "deliver" -> in(input);
                case "out", "ship" -> out(input);
                case "quit", "exit" -> {
                    return;
                }
                default -> println("Unknown command");
            }
        }
    }

    private void help() {
        println("""
                    Usage example:
                    * deliver 10 iron
                    * ship 10 iron
                    
                    Supported commands:
                    * help - show this message
                    * list - show all storage contents
                    * deliver - add contents to storage
                    * ship - move existing content out of storage
                    
                    Supported product types:
                    * iron
                    * water
                    """);
    }

    private void list() {
        var summary = warehouse.summary();
        summary.forEach((k, v) -> System.out.printf("%-10s : %d%n", k, v));
    }

    private void in(Input input) {
        println("Delivering " + input);

        var products = ProductFactory.create(input.type, input.quantity);

        warehouse.addAll(products);
    }

    private void out(Input input) {
        println("Shipping " + input);

        var type = ProductMap.getClassByType(input.type);
        var e = warehouse.ship(type, input.quantity);

        if (e.isLeft()) {
            var shipmentError = e.left();
            System.err.printf(
                "Not enough %s: requested %d, avalialble %d. Try: ship %s %d%n",
                shipmentError.type().getSimpleName(),
                shipmentError.requested(),
                shipmentError.available(),
                shipmentError.type().getSimpleName().toLowerCase(),
                shipmentError.available()
            );

            return;
        }

        var shipped = e.right();
        System.out.printf("Shipped %d × %s%n", shipped.size(), type.getSimpleName());
    }

    private record Input(String type, int quantity) {}
}
