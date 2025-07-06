package com.warehouse.service;

import com.warehouse.beans.Warehouse;
import com.warehouse.dao.WarehouseDao;

import java.util.List;

public class WarehouseService {
    private WarehouseDao warehouseDao;

    public void setWarehouseDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseDao.getAllWarehouses();
    }

    public Warehouse getWarehouseById(int id) {
        return warehouseDao.getWarehouseById(id);
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouseDao.addWarehouse(warehouse);
    }

    public void updateWarehouse(Warehouse warehouse) {
        warehouseDao.updateWarehouse(warehouse);
    }

    public void deleteWarehouse(int id) {
        warehouseDao.deleteWarehouse(id);
    }
}
