package com.warehouse.beans;

import java.sql.Timestamp;

public class Warehouse {
    private int warehouseId;
    private String warehouseName;
    private String warehouseAddress;
    private String warehouseLevel;
    private String warehouseLocation;
    private String warehouseLineNumber;
    private Timestamp createdAt;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseLevel() {
        return warehouseLevel;
    }

    public void setWarehouseLevel(String warehouseLevel) {
        this.warehouseLevel = warehouseLevel;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getWarehouseLineNumber() {
        return warehouseLineNumber;
    }

    public void setWarehouseLineNumber(String warehouseLineNumber) {
        this.warehouseLineNumber = warehouseLineNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
