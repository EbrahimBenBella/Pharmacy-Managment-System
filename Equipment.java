package com.mycompany.pharmacymanagment;

public class Equipment extends PharmacyItems implements Rentable {
    private String equipmentID;
    private String model;
    private double rent_per_hour;
    private String condition;
    private int HoursRented;
    

    public Equipment(int itemId, String Name,int quantity,String equipmentID, String model, double rent_per_hour, String condition, int HoursRented) {
        super(itemId, Name,quantity);
        
        if (equipmentID == null || equipmentID.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment ID cannot be null or empty");
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
        if (rent_per_hour < 0) {
            throw new IllegalArgumentException("Rent per hour cannot be negative");
        }
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be null or empty");
        }
        if (HoursRented < 0) {
            throw new IllegalArgumentException("Hours rented cannot be negative");
        }
        
        this.equipmentID = equipmentID;
        this.model = model;
        this.rent_per_hour = rent_per_hour;
        this.condition = condition;
        this.HoursRented = HoursRented;
    }
    //getters
    public String getEquipmentID() {
        return equipmentID;
    }
    public String getModel() {
        return model;
    }
    public double getRentalPrice() {
        return rent_per_hour;
    }
    public String getCondition() {
        return condition;
    }
    //setters
    public void setCondition(String condition) {
        if (condition == null || condition.trim().isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be null or empty");
        }
        this.condition = condition;
    }
    public void setRentalPrice(double rent_per_hour) {
        if (rent_per_hour < 0) {
            throw new IllegalArgumentException("Rent per hour cannot be negative");
        }
        this.rent_per_hour = rent_per_hour;
    }
    //methods
    @Override
    public boolean isAvailableForRent() throws OutOfStockException {
        return isAvailable();
    }
    @Override
    public double getrentotalPrice() {
        return getRentalPrice() * HoursRented;
    }

        
    
    public void returnEquipment(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Return quantity must be positive");
        }
        setQuantity(getQuantity() + quantity);
        System.out.println("Returned " + quantity + " of " + getName());
    }
    @Override
    public void rent() throws OutOfStockException {
        if (!isAvailableForRent()) {
            throw new OutOfStockException(getName() + " is not available for rent");
        }
        setQuantity(getQuantity() - 1);
    }
    @Override
    public String PrintOrderDetails() {
        System.out.println("-----------------------------------------");
        return String.format("Equipment ID: %s\nName: %s\nRent per hour: %.2f\nHours rented: %d\nTotal Rent: %.2f",
            equipmentID, getName(), rent_per_hour, HoursRented, getrentotalPrice());
    }

    @Override
    public int compareTo(PharmacyItems other) {
        return this.getName().compareTo(other.getName());
    }
    @Override
    public String printDetails() {
        return super.printDetails() + 
               String.format(", Equipment ID: %s, Model: %s, Rental Price: %.2f, Condition: %s",
                           equipmentID, model, rent_per_hour, condition);
    }

}
