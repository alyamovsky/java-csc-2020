package edu.lesson4.inventory.core.product;

import java.util.UUID;

public interface Product {
    UUID getId();

    String getName();

    Rarity getRarity();

    enum Rarity {
        COMMON,
        RARE,
        LEGENDARY
    }
}
