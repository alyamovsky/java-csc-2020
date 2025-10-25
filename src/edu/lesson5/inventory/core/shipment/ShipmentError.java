package edu.lesson5.inventory.core.shipment;

import edu.lesson5.inventory.core.product.Product;

public record ShipmentError(Class<? extends Product> type, int requested, int available, String message) {}
