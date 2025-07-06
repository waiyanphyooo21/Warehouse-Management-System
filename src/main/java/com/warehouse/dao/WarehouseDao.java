package com.warehouse.dao;

import com.warehouse.beans.Warehouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class WarehouseDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Warehouse> getAllWarehouses() {
        String sql = "SELECT * FROM warehouse ORDER BY warehouse_id ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Warehouse.class));
    }

    public Warehouse getWarehouseById(int id) {
        String sql = "SELECT * FROM warehouse WHERE warehouse_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Warehouse.class), id);
    }

    public void addWarehouse(Warehouse warehouse) {
        String sql = "INSERT INTO warehouse (warehouse_name, warehouse_address, warehouse_level, warehouse_location, warehouse_line_number) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                warehouse.getWarehouseName(),
                warehouse.getWarehouseAddress(),
                warehouse.getWarehouseLevel(),
                warehouse.getWarehouseLocation(),
                warehouse.getWarehouseLineNumber());
    }

    public void updateWarehouse(Warehouse warehouse) {
        String sql = "UPDATE warehouse SET warehouse_name = ?, warehouse_address = ?, warehouse_level = ?, warehouse_location = ?, warehouse_line_number = ? WHERE warehouse_id = ?";
        jdbcTemplate.update(sql,
                warehouse.getWarehouseName(),
                warehouse.getWarehouseAddress(),
                warehouse.getWarehouseLevel(),
                warehouse.getWarehouseLocation(),
                warehouse.getWarehouseLineNumber(),
                warehouse.getWarehouseId());
    }

    public void deleteWarehouse(int id) {
        String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
