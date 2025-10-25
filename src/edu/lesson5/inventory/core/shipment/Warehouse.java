package edu.lesson5.inventory.core.shipment;

import edu.lesson5.inventory.core.box.Box;
import edu.lesson5.inventory.core.box.ImmutableBox;
import edu.lesson5.inventory.core.product.Product;
import edu.lesson5.inventory.util.either.Either;
import edu.lesson5.inventory.util.either.Left;
import edu.lesson5.inventory.util.either.Right;

import java.util.*;

public class Warehouse {
    private final Map<Class<? extends Product>, Deque<Box<? extends Product>>> storage = new HashMap<>();

    public void addAll(List<? extends Product> batch) {
        for (Product product : batch) {
            storage.computeIfAbsent(product.getClass(), _ -> new ArrayDeque<>()).push(ImmutableBox.of(product));
        }
    }

    public <T extends Product> Either<ShipmentError, List<T>> ship(Class<T> type, int qty) {
        var stack = storage.get(type);

        var stackSize = stack.size();
        if (stackSize < qty) {
            return new Left<>(new ShipmentError(type, qty, stackSize, ""));
        }

        var result = new ArrayList<T>();

        for (var i = 0; i < qty; i++) {
            Box<? extends Product> box = stack.pop();
            result.add(type.cast(box.get()));
        }

        return new Right<>(result);
    }

    public Map<String, Long> summary() {
        Map<String, Long> out = new LinkedHashMap<>();
        for (var entry : storage.entrySet()) {
            String key = entry.getKey().getSimpleName();
            long count = entry.getValue().size();
            out.put(key, count);
        }
        return Collections.unmodifiableMap(out);
    }
}
