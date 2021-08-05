package com.codegym.service;

import com.codegym.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDao {
    public void insertCustomer(Customer customer) throws SQLException;
    public Customer selectCustomerById(int id);
    public int selectSalaryById(int id);
    public List<Customer> selectAllCustomer();
    public boolean isDeleteCustomer(int id) throws SQLException;
    public boolean isUpdateCustomer(Customer customer, Customer customer2) throws SQLException;
    public boolean UpdateSalary(Customer customer) throws SQLException;
}
