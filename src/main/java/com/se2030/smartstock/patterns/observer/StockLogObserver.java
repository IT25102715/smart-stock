package com.se2030.smartstock.patterns.observer;

import com.se2030.smartstock.model.Product;
import org.springframework.stereotype.Component;

// Prints every stock change to the console
@Component
public class StockLogObserver implements StockObserver {

    @Override
    public void onStockChanged(Product product) {
        System.out.println("Stock updated: " + product.getName() + " now has " + product.getQuantity());
    }
}
