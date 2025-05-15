package com.mycompany.pharmacymanagment;
import java.time.LocalDate;

public class HealthCareProduct extends PharmacyItems implements Sellable {
    private String type;
    private String brand;
    private double sellingprice;
    private LocalDate expiryDate;
    private int quantitysold;

    public HealthCareProduct(int itemId, String Name, double sellingprice, LocalDate expiryDate, int quantity, String brand, String type, int quantitysold) 
            throws InvalidPrescriptionException {
        super(itemId, Name, quantity);
        
        if (sellingprice < 0) {
            throw new IllegalArgumentException("Selling price cannot be negative");
        }
        if (expiryDate == null) {
            throw new InvalidPrescriptionException("Expiry date cannot be null");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidPrescriptionException("Product is already expired");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (quantitysold < 0) {
            throw new IllegalArgumentException("Quantity sold cannot be negative");
        }

        this.sellingprice = sellingprice;
        this.expiryDate = expiryDate;
        this.brand = brand;
        this.type = type;
        this.quantitysold = quantitysold;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be null or empty");
        }
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        this.type = type;
    }

    public double getPrice() {
        return sellingprice;
    }

    public void setSellingprice(double sellingprice) {
        if (sellingprice < 0) {
            throw new IllegalArgumentException("Selling price cannot be negative");
        }
        this.sellingprice = sellingprice;
    }

    @Override
    public void sell() throws OutOfStockException {
        if (!isAvailable()) {
            throw new OutOfStockException("Product " + getName() + " is out of stock");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new OutOfStockException("Product " + getName() + " has expired");
        }
        setQuantity(getQuantity() - quantitysold);
    }

    public int getquantitysold() {
        return quantitysold;
    }

    @Override
    public String printDetails() {
        return super.printDetails() + 
               String.format(", Brand: %s, Type: %s, Selling Price: %.2f, Expiry Date: %s",
                           brand, type, sellingprice, expiryDate);
    }

    @Override
    public String PrintOrderDetails() {
        return String.format("Name: %s\nBrand: %s\nType: %s\nSelling Price: %.2f\nQuantity: %d",
            getName(), brand, type, sellingprice, quantitysold);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
