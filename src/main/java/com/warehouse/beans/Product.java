package com.warehouse.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {

    private int productId;              // maps to stock_id
    private String productName;         // stock_name
    private String productCode;         // product_code
    private String stockLocation;
    private String stockRemark;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private int categoryId;
    private Category category;          // optional for joins/view
    private int unitId;
    private Unit unit;                  // optional for joins/view
    private int supplierId;
    private int warehouseId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // --- Getters & Setters ---
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

    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public int getUnitId() {
        return unitId;
    }
    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
