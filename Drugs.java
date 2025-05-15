package com.mycompany.pharmacymanagment;
import java.time.LocalDate;


public class Drugs extends PharmacyItems implements Sellable {
    private String genericname;
    private String drugID;
    private double sellingprice;
    private boolean requiresPrescription;
    private LocalDate expiryDate;
    private int quantitysold;

    @Override
    public int compareTo(PharmacyItems other) {
        return this.getName().compareTo(other.getName());
    }
   
    public Drugs(int itemId, String Name, double sellingprice, LocalDate expiryDate, String genericname, String drugID, boolean requiresPrescription, int quantity, int quantitysold) 
            throws InvalidPrescriptionException {
        super(itemId, Name, quantity);
        
        if (sellingprice < 0) {
            throw new IllegalArgumentException("Selling price cannot be negative");
        }
        if (expiryDate == null) {
            throw new InvalidPrescriptionException("Expiry date cannot be null");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidPrescriptionException("Drug is already expired");
        }
        if (genericname == null || genericname.trim().isEmpty()) {
            throw new InvalidPrescriptionException("Generic name cannot be null or empty");
        }
        if (drugID == null || drugID.trim().isEmpty()) {
            throw new InvalidPrescriptionException("Drug ID cannot be null or empty");
        }
        
        this.genericname = genericname;
        this.drugID = drugID;
        this.requiresPrescription = requiresPrescription;
        this.expiryDate = expiryDate;
        this.quantitysold = quantitysold;
        this.sellingprice = sellingprice;
    }
    public String getGenericname() {
        return genericname;
    }
    public int getquantitysold() {
        return quantitysold;
    }
    public String getDrugID() {
        return drugID;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) throws InvalidPrescriptionException {
        if (expiryDate == null) {
            throw new InvalidPrescriptionException("Expiry date cannot be null");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidPrescriptionException("Cannot set expired date");
        }
        this.expiryDate = expiryDate;
    }
    public boolean RequiresPrescription() {
        return requiresPrescription;
    }
    public boolean needsPrescription() {
        return requiresPrescription;
    }
    @Override
    public void sell() throws OutOfStockException, InvalidPrescriptionException {
        if (!isAvailable()) {
            throw new OutOfStockException("Drug " + getName() + " is out of stock");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new OutOfStockException("Drug " + getName() + " has expired");
        }
        if (requiresPrescription) {
            throw new InvalidPrescriptionException("Drug " + getName() + " requires a prescription");
        }
        setQuantity(getQuantity() - quantitysold);
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
    public String printDetails() {
        return super.printDetails() + 
               String.format(", Generic Name: %s, Drug ID: %s, Requires Prescription: %s, Expiry Date: %s, Selling Price: %.2f",
                           genericname, drugID, requiresPrescription, expiryDate, sellingprice);
    }   
    @Override
    public String PrintOrderDetails() {
        return String.format("Drug ID: %s\nName: %s\nSelling Price: %.2f\nQuantity: %d",
            drugID, getName(), sellingprice, quantitysold);
    }
}