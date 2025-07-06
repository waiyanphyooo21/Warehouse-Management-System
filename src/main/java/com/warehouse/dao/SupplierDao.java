package com.warehouse.dao;

import com.warehouse.beans.Supplier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SupplierDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Supplier> getAllSuppliers() {
        String sql = "SELECT * FROM supplier ORDER BY supplier_id ASC ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Supplier.class));
    }

    public Supplier getSupplierById(int id) {
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Supplier.class), id);
    }

    public void addSupplier(Supplier supplier) {
        System.out.println("DAO addSupplier called: " + supplier.getSupplierName());
        String sql = "INSERT INTO supplier (supplier_name, supplier_ph, supplier_address) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, supplier.getSupplierName(), supplier.getSupplierPh(), supplier.getSupplierAddress());
        System.out.println("Insert executed.");
    }

    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET supplier_name = ?, supplier_ph = ?, supplier_address = ? WHERE supplier_id = ?";
        jdbcTemplate.update(sql, supplier.getSupplierName(), supplier.getSupplierPh(), supplier.getSupplierAddress(), supplier.getSupplierId());
    }

    public void deleteSupplier(int id) {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
