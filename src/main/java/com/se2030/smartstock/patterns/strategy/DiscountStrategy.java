package com.se2030.smartstock.patterns.strategy;

// Strategy: each class is one way of working out the discount
public interface DiscountStrategy {

    double apply(double total);
}
