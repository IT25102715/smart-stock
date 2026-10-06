package com.se2030.smartstock.patterns.observer;

import com.se2030.smartstock.model.Product;

// Observer: gets told whenever a product's stock changes
public interface StockObserver {

    void onStockChanged(Product product);
}
