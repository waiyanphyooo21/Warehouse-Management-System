package com.warehouse.beans;

public class Supplier {
    private Integer supplierId; // <- changed from int to Integer
    private String supplierName;
    private String supplierPh;
    private String supplierAddress;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPh() {
        return supplierPh;
    }

    public void setSupplierPh(String supplierPh) {
        this.supplierPh = supplierPh;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
}
