package com.se2030.smartstock.patterns.observer;

import com.se2030.smartstock.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Keeps a list of products that are running low
@Component
public class LowStockObserver implements StockObserver {

    private static final int THRESHOLD = 10;

    private final Map<Long, String> alerts = new ConcurrentHashMap<>();

    @Override
    public void onStockChanged(Product product) {
        if (product.getQuantity() <= THRESHOLD) {
            alerts.put(product.getId(), product.getName() + " is low on stock (" + product.getQuantity() + " left)");
        } else {
            alerts.remove(product.getId());
        }
    }

    public List<String> getAlerts() {
        return new ArrayList<>(alerts.values());
    }
}
