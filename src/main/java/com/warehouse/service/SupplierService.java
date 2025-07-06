package com.warehouse.service;

import com.warehouse.beans.Supplier;
import com.warehouse.dao.SupplierDao;  // use SupplierDao, not SupplierDAO

import java.util.List;

public class SupplierService {
    private SupplierDao supplierDao;  // lowercase 'd'

    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAllSuppliers();
    }

    public Supplier getSupplierById(int id) {
        return supplierDao.getSupplierById(id);
    }

    public void addSupplier(Supplier supplier) {
        System.out.println("Service addSupplier called: " + supplier.getSupplierName());
        supplierDao.addSupplier(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        supplierDao.updateSupplier(supplier);
    }

    public void deleteSupplier(int id) {
        supplierDao.deleteSupplier(id);
    }
}
