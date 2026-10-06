package com.se2030.smartstock.patterns.strategy;

// Picks the strategy that matches the type chosen on the form
public class DiscountStrategies {

    public static DiscountStrategy forType(String type) {
        if ("BULK".equals(type)) {
            return new BulkDiscount();
        }
        if ("FLAT".equals(type)) {
            return new FlatDiscount();
        }
        return new NoDiscount();
    }
}
