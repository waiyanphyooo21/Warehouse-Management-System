package com.warehouse.controllers;

import com.warehouse.beans.Category;
import com.warehouse.beans.Product;
import com.warehouse.service.CategoryService;
import com.warehouse.service.ProductService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService; // inject categoryService too

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/products")
    public String showProductList(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        List<Product> products = productService.getAllProducts();
        if (products == null) {
            products = new ArrayList<>();
        }

        model.addAttribute("products", products);
        model.addAttribute("active", "product");
        return "product";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        model.addAttribute("product", new Product());

        List<Category> categories = categoryService.getAllCategories();
        System.out.println("Categories count: " + (categories == null ? "null" : categories.size()));
        model.addAttribute("categories", categories);

        model.addAttribute("active", "product");
        return "product-form";
    }


    @PostMapping("/products/add")
    public String saveNewProduct(@ModelAttribute Product product, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Product product = productService.getProductById(id);
        if (product == null) return "redirect:/products";

        // Set categoryId for the form select field
        if (product.getCategory() != null) {
            product.setCategoryId(product.getCategory().getCategoryId());
        }

        model.addAttribute("product", product);

        // Add categories list for dropdown
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("active", "product");
        return "product-form";
    }

    @PostMapping("/products/edit")
    public String updateProduct(@ModelAttribute Product product, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
