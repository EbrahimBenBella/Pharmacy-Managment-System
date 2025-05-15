package com.mycompany.pharmacymanagment;

public class ExpiredPrescriptionException extends Exception {
    public ExpiredPrescriptionException(String message) {
        super(message);
    }
} 