package com.warehouse.service;

import com.warehouse.beans.Product;
import com.warehouse.dao.ProductDao;

import java.util.List;

public class ProductService {

    private ProductDao productDao;  // no final, allows setter injection

    // Default constructor needed for Spring to instantiate
    public ProductService() {
    }

    // Setter method for Spring to inject the DAO
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public int saveProduct(Product product) {
        return productDao.saveProduct(product);
    }

    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    public int deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }
}
