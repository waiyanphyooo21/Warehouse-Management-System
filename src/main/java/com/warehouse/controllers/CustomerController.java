package com.warehouse.controllers;

import com.warehouse.beans.Customer;
import com.warehouse.service.CustomerService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model, HttpSession session) {
//        System.out.println("DEBUG: /customers controller called");
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        List<Customer> customers = customerService.getAllCustomers();
//        System.out.println("DEBUG: customers found = " + (customers == null ? "null" : customers.size()));

        if(customers == null) {
            customers = new ArrayList<>(); // avoid null pointer in JSP
        }

        model.addAttribute("customers", customers);
        model.addAttribute("active", "customer");
        return "customer";
    }



    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        model.addAttribute("customer", new Customer());
        model.addAttribute("active", "customer");
        return "customer-form";
    }

    @PostMapping("/customers/add")
    public String saveNewCustomer(@ModelAttribute Customer customer, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        Customer customer = customerService.getCustomerById(id);
        if (customer == null) return "redirect:/customers";

        model.addAttribute("customer", customer);
        model.addAttribute("active", "customer");
        return "customer-form";
    }


    @PostMapping("/customers/edit")
    public String updateCustomer(@ModelAttribute Customer customer, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) return "redirect:/login";

        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
