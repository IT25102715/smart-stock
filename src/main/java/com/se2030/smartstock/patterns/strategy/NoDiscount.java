package com.se2030.smartstock.patterns.strategy;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double apply(double total) {
        return total;
    }
}
