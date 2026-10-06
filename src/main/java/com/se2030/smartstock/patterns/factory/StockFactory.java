package com.se2030.smartstock.patterns.factory;

import com.se2030.smartstock.model.Product;
import com.se2030.smartstock.model.Stock;

// Factory method: subclasses decide what kind of Stock record is created
public interface StockFactory {

    Stock create(Product product, int quantity, String remarks);

    static StockFactory forType(String type) {
        return "OUT".equals(type) ? new StockOutFactory() : new StockInFactory();
    }
}
