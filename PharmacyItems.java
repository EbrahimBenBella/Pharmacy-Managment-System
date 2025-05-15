package com.mycompany.pharmacymanagment;

abstract class PharmacyItems implements Comparable<PharmacyItems> {
    private int itemId;
    private String Name;           // Default value
    private int quantity;
    
    public PharmacyItems(int itemId, String Name, int quantity) {
        if (itemId < 0) {
            throw new IllegalArgumentException("Item ID cannot be negative");
        }
        if (Name == null || Name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        this.itemId = itemId;
        this.Name = Name;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    public int getItemId() {
        return itemId;
    }
   
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.Name = name;
    }
    public void setItemId(int itemId) {
        if (itemId < 0) {
            throw new IllegalArgumentException("Item ID cannot be negative");
        }
        this.itemId = itemId;
    }
   
    public boolean isAvailable() throws OutOfStockException {
        if (quantity <= 0) {
            throw new OutOfStockException("Item " + Name + " is out of stock");
        }
        return true;
    }
    

    public String printDetails(){
        return String.format("Item ID: %d, Name: %s, Quantity: %d", itemId, Name, quantity);
    }
    abstract String PrintOrderDetails();
    
    @Override
    public int compareTo(PharmacyItems other) {
        return this.getName().compareTo(other.getName());
    }

}