package com.se2030.smartstock.patterns.factory;

import com.se2030.smartstock.model.Product;
import com.se2030.smartstock.model.Stock;

import java.time.LocalDate;

public class StockOutFactory implements StockFactory {

    @Override
    public Stock create(Product product, int quantity, String remarks) {
        return new Stock(product, "OUT", quantity, LocalDate.now(), remarks);
    }
}
