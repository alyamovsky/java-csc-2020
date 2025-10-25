package edu.lesson5.inventory.core.product;

import java.util.List;
import java.util.stream.Stream;

public class ProductFactory {
    public static List<Product> create(String type, int quantity) {
        var sup = ProductMap.getFactoryByType(type);

        return Stream.generate(sup).limit(quantity).toList();
    }
}
