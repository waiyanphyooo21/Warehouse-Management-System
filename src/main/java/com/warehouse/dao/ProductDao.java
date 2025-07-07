package com.warehouse.dao;

import com.warehouse.beans.Category;
import com.warehouse.beans.Product;
import com.warehouse.beans.Unit;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get all products with category and unit info
    public List<Product> getAllProducts() {
        String sql = "SELECT s.*, c.category_name, u.unit_name " +
                "FROM stock s " +
                "LEFT JOIN category c ON s.category_id = c.category_id " +
                "LEFT JOIN unit u ON s.unit_id = u.unit_id";
        return jdbcTemplate.query(sql, new ProductMapper());
    }

    // Get single product by id with category and unit info
    public Product getProductById(int id) {
        String sql = "SELECT s.*, c.category_name, u.unit_name " +
                "FROM stock s " +
                "LEFT JOIN category c ON s.category_id = c.category_id " +
                "LEFT JOIN unit u ON s.unit_id = u.unit_id " +
                "WHERE s.stock_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Insert new product into stock table, setting created_at and updated_at as current time
    public int saveProduct(Product product) {
        String sql = "INSERT INTO stock (stock_name, category_id, purchase_price, sale_price, stock_location, stock_remark, unit_id, supplier_id, warehouse_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        return jdbcTemplate.update(sql,
                product.getProductName(),
                product.getCategoryId(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getStockLocation(),
                product.getStockRemark(),
                product.getUnitId(),
                product.getSupplierId(),
                product.getWarehouseId(),
                now,
                now);
    }

    // Update existing product in stock table, including updated_at timestamp
    public int updateProduct(Product product) {
        String sql = "UPDATE stock SET stock_name = ?, category_id = ?, purchase_price = ?, sale_price = ?, stock_location = ?, stock_remark = ?, " +
                "unit_id = ?, supplier_id = ?, warehouse_id = ?, updated_at = ? WHERE stock_id = ?";

        Timestamp now = new Timestamp(System.currentTimeMillis());

        return jdbcTemplate.update(sql,
                product.getProductName(),
                product.getCategoryId(),
                product.getPurchasePrice(),
                product.getSalePrice(),
                product.getStockLocation(),
                product.getStockRemark(),
                product.getUnitId(),
                product.getSupplierId(),
                product.getWarehouseId(),
                now,
                product.getProductId());
    }

    // Delete product from stock table
    public int deleteProduct(int id) {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Mapper class to map ResultSet rows to Product objects
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
            product.setUpdatedAt(rs.getTimestamp("updated_at"));

            // Map Category object
            Category category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            product.setCategory(category);

            // Map Unit object
            Unit unit = new Unit();
            unit.setUnitId(rs.getInt("unit_id"));
            unit.setUnitName(rs.getString("unit_name"));
            product.setUnit(unit);

            return product;
        }
    }
}
