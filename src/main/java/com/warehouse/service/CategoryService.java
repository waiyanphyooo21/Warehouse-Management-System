package com.warehouse.service;

import com.warehouse.beans.Category;
import com.warehouse.dao.CategoryDao;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao;  // no final, for setter injection

    // Default constructor for Spring
    public CategoryService() {}

    // Setter for setter injection
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    // Add this method to get a Category by its ID
    public Category getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }
}
