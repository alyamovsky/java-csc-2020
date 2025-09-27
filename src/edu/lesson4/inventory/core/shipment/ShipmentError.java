package edu.lesson4.inventory.core.shipment;

import edu.lesson4.inventory.core.product.Product;

public record ShipmentError(Class<? extends Product> type, int requested, int available, String message) {}
