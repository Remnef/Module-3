package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.util.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICustomerDao {
    private final String INSERT = "insert into customer(name, email, salary) values (?,?,?);";
    private final String SELECT_BY_ID = "select id, name, email, salary from customer where id=?;";

    private final String DELETE = "delete from customer where id =?;";
    private final String UPDATE = "update customer set name = ?, email= ?, salary= ? where id= ?;";
    private final String UPDATE_SALARY = "update customer set salary = ? where id = ?;";

    public CustomerDao(){

    }

    Connection connection = MySQLConnUtils.getConnection();

    @Override
    public void insertCustomer(Customer customer) throws SQLException {
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getEmail());
            statement.setInt(3,customer.getSalary());
            statement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public Customer selectCustomerById(int id) {
        Customer customer = null;
        try{
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet rs =statement.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customer = new Customer(id, name, email, salary);
            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return  customer;
    }

    @Override
    public int selectSalaryById(int id) {
        return 0;
    }

    @Override
    public List<Customer> selectAllCustomer() {
        final String SELECT_ALL = "select * from customer;";

        List<Customer> customers = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int salary = rs.getInt("salary");
                customers.add(new Customer(id, name, email, salary));
            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public boolean isDeleteCustomer(int id) throws SQLException {
        boolean rowdeleted = false;
        try{
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setInt(1,id);
            rowdeleted = statement.executeUpdate() >0;

        }catch(SQLException e){
            printSQLException(e);
        }
        return rowdeleted;
    }

    public boolean UpdateCustomer(Customer customer) throws SQLException{
        boolean rowUpdated = false;
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getEmail());
            statement.setInt(3,customer.getSalary());
            statement.setInt(4,customer.getId());
            statement.executeUpdate();
            rowUpdated = statement.executeUpdate() >0;
            connection.commit();
            connection.setAutoCommit(true);
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean isUpdateCustomer(Customer customer, Customer customer2) throws SQLException {
        boolean rowUpdated = false;
        try{
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            PreparedStatement statement2 = connection.prepareStatement(UPDATE);

            statement.setString(1,customer.getName());
            statement.setString(2,customer.getEmail());
            statement.setInt(3,customer.getSalary());
            statement.setInt(4,customer.getId());
            rowUpdated = statement.executeUpdate() >0;

            statement2.setString(1,customer2.getName());
            statement2.setString(2,customer2.getEmail());
            statement2.setInt(3,customer2.getSalary());
            statement2.setInt(4,customer2.getId());
            rowUpdated = statement2.executeUpdate() >0;

            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean UpdateSalary(Customer customer) throws SQLException {
        boolean Update = false;
        try{
            PreparedStatement statement = connection.prepareStatement(UPDATE_SALARY);
            statement.setInt(1, customer.getSalary());
            statement.setInt(2,customer.getId());
            Update = statement.executeUpdate() >0;
        }catch (SQLException e){
            printSQLException(e);
        }
        return Update;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
