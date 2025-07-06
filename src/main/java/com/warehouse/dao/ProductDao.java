package com.warehouse.dao;

import com.warehouse.beans.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

public class ProductDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    // Use stock table and columns from DDL
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM stock";
        return template.query(sql, new ProductMapper());
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        try {
            return template.queryForObject(sql, new Object[]{id}, new ProductMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Insert into stock table, set required columns
    public int saveProduct(Product product) {
        String sql = "INSERT INTO stock (stock_name, category_id, purchase_price, sale_price, stock_location, stock_remark, unit_id, supplier_id, warehouse_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return template.update(sql,
                product.getProductName(),
                product.getCategoryId(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getStockLocation(),
                product.getStockRemark(),
                product.getUnitId(),
                product.getSupplierId(),
                product.getWarehouseId());
    }

    // Update stock table
    public int updateProduct(Product product) {
        String sql = "UPDATE stock SET stock_name = ?, category_id = ?, purchase_price = ?, sale_price = ?, stock_location = ?, stock_remark = ?, " +
                "unit_id = ?, supplier_id = ?, warehouse_id = ? WHERE stock_id = ?";
        return template.update(sql,
                product.getProductName(),
                product.getCategoryId(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getStockLocation(),
                product.getStockRemark(),
                product.getUnitId(),
                product.getSupplierId(),
                product.getWarehouseId(),
                product.getProductId());
    }

    public int deleteProduct(int id) {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        return template.update(sql, id);
    }

    private static final class ProductMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getInt("stock_id"));
            product.setProductName(rs.getString("stock_name"));
            product.setCategoryId(rs.getInt("category_id"));
            product.setPurchasePrice(rs.getBigDecimal("purchase_price"));
            product.setSalePrice(rs.getBigDecimal("sale_price"));
            product.setStockLocation(rs.getString("stock_location"));
            product.setStockRemark(rs.getString("stock_remark"));
            product.setUnitId(rs.getInt("unit_id"));
            product.setSupplierId(rs.getInt("supplier_id"));
            product.setWarehouseId(rs.getInt("warehouse_id"));
            product.setCreatedAt(rs.getTimestamp("created_at"));
            return product;
        }
    }
}
