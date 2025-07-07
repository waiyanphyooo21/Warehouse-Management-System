package com.warehouse.dao;

import com.warehouse.beans.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT category_id, category_name FROM category ORDER BY category_name";
        return template.query(sql, new CategoryMapper());
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT category_id, category_name FROM category WHERE category_id = ?";
        try {
            return template.queryForObject(sql, new CategoryMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;  // no category found for this id
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            return category;
        }
    }
}
