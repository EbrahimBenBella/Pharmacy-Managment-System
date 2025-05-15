package com.mycompany.pharmacymanagment;

public interface Rentable {
    public boolean isAvailableForRent() throws OutOfStockException;
    public void rent() throws OutOfStockException;
    public void returnEquipment(int quantity);
    public double getRentalPrice();
    public String getCondition();
    public void setCondition(String condition);
    public void setRentalPrice(double rentalPrice);
    public double getrentotalPrice();
}
