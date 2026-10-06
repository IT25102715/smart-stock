package com.se2030.smartstock.patterns.strategy;

// 10% off big orders
public class BulkDiscount implements DiscountStrategy {

    @Override
    public double apply(double total) {
        return total * 0.90;
    }
}
