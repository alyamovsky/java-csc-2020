package edu.lesson5.inventory.core.product;

import java.util.UUID;

public abstract class BaseProduct implements Product {
    protected final UUID id;
    protected final String name;
    protected final Rarity rarity;

    protected BaseProduct(String name, Rarity rarity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.rarity = rarity;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }
}
