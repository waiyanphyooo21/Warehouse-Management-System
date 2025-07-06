package com.warehouse.service;

import com.warehouse.beans.Customer;
import com.warehouse.dao.CustomerDao;

import java.util.List;

public class CustomerService {

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
    public List<Customer> getRecentCustomers(int limit) {
        return customerDao.getRecentCustomers(limit);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    public int saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    public int deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }
}
