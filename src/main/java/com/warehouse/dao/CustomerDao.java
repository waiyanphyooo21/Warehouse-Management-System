package com.warehouse.dao;

import com.warehouse.beans.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDao {

    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Customer> getRecentCustomers(int limit) {
        String sql = "SELECT * FROM customer ORDER BY created_at ASC LIMIT ?";
        return template.query(sql, new Object[]{limit}, new CustomerMapper());
    }

//    public List<Customer> getAllCustomers() {
//        String sql = "SELECT * FROM customer ORDER BY customer_id ASC";
//        return template.query(sql, new CustomerMapper());
//    }
public List<Customer> getAllCustomers() {
    String sql = "SELECT * FROM customer"; // Use your actual table name
    return template.query(sql, (rs, rowNum) -> {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setCustomerName(rs.getString("customer_name"));
        c.setCustomerPh(rs.getString("customer_ph"));
        c.setCustomerAddress(rs.getString("customer_address"));
        return c;
    });
}


    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            return template.queryForObject(sql, new Object[]{id}, new CustomerMapper());
        } catch (EmptyResultDataAccessException e) {
            // No customer found with given id
            return null;
        }
    }

    public int saveCustomer(Customer customer) {
        String sql = "INSERT INTO customer(customer_name, customer_ph, customer_address) VALUES (?, ?, ?)";
        return template.update(sql,
                customer.getCustomerName(),
                customer.getCustomerPh(),
                customer.getCustomerAddress());
    }

    public int updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET customer_name = ?, customer_ph = ?, customer_address = ? WHERE customer_id = ?";
        return template.update(sql,
                customer.getCustomerName(),
                customer.getCustomerPh(),
                customer.getCustomerAddress(),
                customer.getCustomerId());
    }

    public int deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        return template.update(sql, id);
    }

    private static final class CustomerMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setCustomerPh(rs.getString("customer_ph"));
            customer.setCustomerAddress(rs.getString("customer_address"));
            customer.setCreatedAt(rs.getTimestamp("created_at"));
            return customer;
        }
    }
}
