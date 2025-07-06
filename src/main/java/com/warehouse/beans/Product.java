package com.warehouse.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int productId;          // maps to stock_id
    private String productName;     // stock_name
    private int categoryId;
    private Category category;      // optional for display/join
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private String stockLocation;
    private String stockRemark;
    private int unitId;
    private int supplierId;
    private int warehouseId;
    private Timestamp createdAt;

    // Getters and setters

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getStockLocation() {
        return stockLocation;
    }
    public void setStockLocation(String stockLocation) {
        this.stockLocation = stockLocation;
    }

    public String getStockRemark() {
        return stockRemark;
    }
    public void setStockRemark(String stockRemark) {
        this.stockRemark = stockRemark;
    }

    public int getUnitId() {
        return unitId;
    }
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
