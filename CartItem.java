package com.mycompany.pharmacymanagment;

public class CartItem {
    private String name;
    private int quantity;
    private double price;
    private String itemType;

    public CartItem(String name, int quantity, double price, String itemType) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getItemType() {
        return itemType;
    }

    public double getTotal() {
        return quantity * price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
} 