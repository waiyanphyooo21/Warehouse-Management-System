package com.warehouse.controllers;

import com.warehouse.beans.Category;
import com.warehouse.beans.Product;
import com.warehouse.beans.Unit;
import com.warehouse.service.CategoryService;
import com.warehouse.service.ProductService;
import com.warehouse.service.UnitService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UnitService unitService;

    public ProductController(ProductService productService, CategoryService categoryService, UnitService unitService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.unitService = unitService;
    }

    // LIST PRODUCTS
    @GetMapping
    public String showProductList(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("active", "product");
        return "product";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddProductForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Product product = new Product();  // empty product for form
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("active", "product");
        return "product-form";
    }

    // SAVE NEW PRODUCT
    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute("product") Product product,
                                 @RequestParam("categoryId") int categoryId,
                                 @RequestParam("unitId") int unitId,
                                 HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Category category = categoryService.getCategoryById(categoryId);
        Unit unit = unitService.getUnitById(unitId);
        product.setCategory(category);
        product.setUnit(unit);

        productService.saveProduct(product);
        return "redirect:/products";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Product product = productService.getProductById(id);
        if (product == null) return "redirect:/products";

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("active", "product");
        return "product-form";
    }

    // UPDATE PRODUCT
    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product,
                                @RequestParam("categoryId") int categoryId,
                                @RequestParam("unitId") int unitId,
                                HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Category category = categoryService.getCategoryById(categoryId);
        Unit unit = unitService.getUnitById(unitId);
        product.setCategory(category);
        product.setUnit(unit);

        productService.updateProduct(product);
        return "redirect:/products";
    }

    // DELETE PRODUCT
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
