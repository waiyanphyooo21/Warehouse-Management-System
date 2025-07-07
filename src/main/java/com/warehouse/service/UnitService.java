package com.warehouse.service;

import com.warehouse.beans.Unit;
import com.warehouse.dao.UnitDao;

import java.util.List;

public class UnitService {

    private UnitDao unitDao;

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    public List<Unit> getAllUnits() {
        return unitDao.getAllUnits();
    }

    // Added method to get Unit by ID
    public Unit getUnitById(int id) {
        return unitDao.getUnitById(id);
    }
}
