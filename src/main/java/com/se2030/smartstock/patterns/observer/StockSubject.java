package com.se2030.smartstock.patterns.observer;

import com.se2030.smartstock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// Subject: tells every registered observer about a stock change
@Component
public class StockSubject {

    // Spring fills this with all StockObserver beans
    @Autowired
    private List<StockObserver> observers;

    public void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.onStockChanged(product);
        }
    }
}
