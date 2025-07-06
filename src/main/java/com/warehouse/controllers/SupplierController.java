package com.warehouse.controllers;

import com.warehouse.beans.Supplier;
import com.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @GetMapping("/list")
    public String listSuppliers(Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("active", "supplier");
        return "supplier"; // The JSP that shows the list
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("active", "supplier");
        return "supplier-form";
    }

    @PostMapping("/add")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/supplier/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        model.addAttribute("active", "supplier");
        return "supplier-form";
    }

    @PostMapping("/edit")
    public String updateSupplier(@ModelAttribute Supplier supplier) {
        supplierService.updateSupplier(supplier);
        return "redirect:/supplier/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") int id) {
        supplierService.deleteSupplier(id);
        return "redirect:/supplier/list";
    }
}
