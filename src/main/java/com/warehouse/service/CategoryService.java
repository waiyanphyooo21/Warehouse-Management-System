package com.warehouse.service;

import com.warehouse.beans.Category;
import com.warehouse.dao.CategoryDao;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
