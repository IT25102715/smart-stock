package com.se2030.smartstock.patterns.strategy;

// 5% off for regular suppliers
public class FlatDiscount implements DiscountStrategy {

    @Override
    public double apply(double total) {
        return total * 0.95;
    }
}
