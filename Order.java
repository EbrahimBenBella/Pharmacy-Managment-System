package com.mycompany.pharmacymanagment;
import java.time.LocalDate;
import java.util.ArrayList;


public class Order {
    private int orderId=0;
    private Member member;
    private LocalDate orderDate;
    private ArrayList<PharmacyItems> items;
    private String paymentmethod;

    Order(int orderId, Member member, LocalDate orderDate, String paymentmethod, ArrayList<PharmacyItems> items) 
            throws OutOfStockException, InvalidPrescriptionException, PaymentException {
        if (orderId < 0) {
            throw new IllegalArgumentException("Order ID cannot be negative");
        }
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date cannot be null");
        }
        if (paymentmethod == null || paymentmethod.trim().isEmpty()) {
            throw new PaymentException("Payment method cannot be null or empty");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items list cannot be null or empty");
        }

        this.orderId = orderId;
        this.member = member;
        this.orderDate = orderDate;
        this.paymentmethod = paymentmethod;
        this.items = new ArrayList<>(items); // Create a copy of the list

        processItems();
    }

    private void processItems() throws OutOfStockException, InvalidPrescriptionException {
        for (PharmacyItems item : items) {
            if (item instanceof Sellable) {
                ((Sellable) item).sell();
                if(item instanceof HealthCareProduct){
                   
                    if(!item.isAvailable()){
                       
                    }
                }
                if(item instanceof Drugs){
                    if (!((item.isAvailable())&&(!(((Drugs)item).needsPrescription())))){
                   
                    }
                }
            } else if (item instanceof Rentable) {
                ((Rentable) item).rent();
            }
        }
    }

    public String getPaymentMethod() {
        return paymentmethod;
    }

    public void addItem(PharmacyItems item) throws OutOfStockException, InvalidPrescriptionException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        // Try to process the item before adding
        if (item instanceof Sellable) {
            ((Sellable) item).sell();
        } else if (item instanceof Rentable) {
            ((Rentable) item).rent();
        }
        
        items.add(item);
    }

    public void removeItem(PharmacyItems item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.remove(item);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (PharmacyItems item : items) {
            if (item instanceof Sellable) {
                total += ((Sellable) item).getPrice() * ((Sellable) item).getquantitysold();
            } else if (item instanceof Rentable) {
                total += ((Rentable) item).getrentotalPrice();
            }
        }
        return total;
    }

    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Member Name: " + member.getName());
        System.out.println("Order Date: " + orderDate);
        System.out.println("Payment method: " + paymentmethod);
        System.out.println("Total Price: " + calculateTotalPrice());
        System.out.println("Items in the order:");

        for (PharmacyItems item : items) {
            System.out.println(item.PrintOrderDetails());
        }
    }

    public int getOrderId() {
        return orderId;
    }
}
