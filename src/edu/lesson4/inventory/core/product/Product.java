package edu.lesson4.inventory.core.product;

public interface Product {
    String getId();

    String getName();

    Rarity getRarity();

    enum Rarity {
        COMMON,
        RARE,
        LEGENDARY
    }
}
