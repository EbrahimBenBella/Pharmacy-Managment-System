package com.mycompany.pharmacymanagment;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
} 