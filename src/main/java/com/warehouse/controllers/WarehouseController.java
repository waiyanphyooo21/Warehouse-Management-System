package com.warehouse.controllers;

import com.warehouse.beans.Warehouse;
import com.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    public void setWarehouseService(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("/list")
    public String listWarehouses(Model model) {
        model.addAttribute("warehouses", warehouseService.getAllWarehouses());
        model.addAttribute("active", "warehouse");
        return "warehouse"; // warehouse.jsp
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("active", "warehouse");
        return "warehouse-form";
    }

    @PostMapping("/add")
    public String addWarehouse(@ModelAttribute Warehouse warehouse) {
        warehouseService.addWarehouse(warehouse);
        return "redirect:/warehouse/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("active", "warehouse");
        return "warehouse-form";
    }

    @PostMapping("/edit")
    public String updateWarehouse(@ModelAttribute Warehouse warehouse) {
        warehouseService.updateWarehouse(warehouse);
        return "redirect:/warehouse/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable("id") int id) {
        warehouseService.deleteWarehouse(id);
        return "redirect:/warehouse/list";
    }
}
