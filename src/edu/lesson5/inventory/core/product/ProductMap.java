package edu.lesson5.inventory.core.product;

import java.util.Map;
import java.util.function.Supplier;

public class ProductMap {
    private static final Map<String, Class<? extends Product>> CLASS_REGISTRY = Map.of(
        "metal",
        Metal.class,
        "liquid",
        Liquid.class
    );
    private static final Map<String, Supplier<Product>> FACTORY_REGISTRY = Map.of(
        "metal",
        () -> new Metal("Iron Ore", Product.Rarity.COMMON),
        "liquid",
        () -> new Liquid("Clear Water", Product.Rarity.COMMON)
    );

    private ProductMap() {
    }

    public static Class<? extends Product> getClassByType(String type) {
        return CLASS_REGISTRY.get(type);
    }

    public static Supplier<Product> getFactoryByType(String type) {
        return FACTORY_REGISTRY.get(type);
    }
}
