package com.warehouse.controllers;

import com.warehouse.beans.Customer;
import com.warehouse.service.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("active", "dashboard");

        model.addAttribute("customerCount", 125);
        model.addAttribute("supplierCount", 42);
        model.addAttribute("productCount", 856);
        model.addAttribute("totalStock", 5240);

        List<Customer> recentCustomers = customerService.getRecentCustomers(5); // limit 5 or whatever you want
        model.addAttribute("recentCustomers", recentCustomers);

        return "dashboard";
    }
}
