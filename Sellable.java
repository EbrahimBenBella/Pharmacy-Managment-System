package com.mycompany.pharmacymanagment;

public interface Sellable {
     void sell() throws OutOfStockException, InvalidPrescriptionException;
     double getPrice();
     int getquantitysold();

}
