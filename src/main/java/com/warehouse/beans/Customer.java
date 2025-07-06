package com.warehouse.beans;

import java.sql.Timestamp;

public class Customer {
    private int customerId;
    private String customerName;
    private String customerPh;
    private String customerAddress;
    private Timestamp createdAt;

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerPh() {
        return customerPh;
    }
    public void setCustomerPh(String customerPh) {
        this.customerPh = customerPh;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
