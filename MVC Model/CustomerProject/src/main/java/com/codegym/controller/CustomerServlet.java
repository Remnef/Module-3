package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    private static final long serverVerionUID = 1L;

    private CustomerDao customerDAO;

    public void init(){
        customerDAO = new CustomerDao();
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action) {
                case "create": {
                    insertCustomer(request, response);
                    break;
                }
                case "edit": {
                    updateCustomer(request, response);
                    break;
                }
                case "sent": {
                    sentSalary(request, response);
                    break;
                }

                default: {
                    listCustomer(request,response);
                    break;
                }
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        try {
            switch (action) {
                case "create": {
                    showCreateCustomer(request,response);
                    break;

                }
                case "edit": {
                    showEditCustomer(request, response);
                    break;
                }
                case "sent": {
                    showSentSalary(request, response);
                    break;
                }
                case "delete":
                {
                    deleteCustomer(request,response);
                }
                default: {
                    listCustomer(request,response);
                    break;
                }
            }
        }catch (SQLException e){
            throw new ServletException(e);
        }
    }

    public void updateCustomer(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Customer editCustomer = new Customer(id,name,email,salary);
        boolean check = customerDAO.UpdateCustomer(editCustomer);
        request.setAttribute("message",(check)?"New customer was edited":"Cannt Edit !!!");
        response.sendRedirect("/customer");
    }

    public void listCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, SQLException, ServletException {
        List<Customer> customers = customerDAO.selectAllCustomer();
        String mess = request.getParameter("message");
        if (mess == null){
            mess = "";
        }
        request.setAttribute("mess",mess);
        request.setAttribute("customers" +
                "",customers);
        System.out.println(customers);
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/customer/index.jsp");
        dis.forward(request, response);
    }

    public void showCreateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/customer/create.jsp");
        dis.forward(request,response);
    }

    public void showEditCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = customerDAO.selectCustomerById(id);
        request.setAttribute("customers",existingCustomer);
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/customer/edit.jsp");
        dis.forward(request,response);
    }

    public void showSentSalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existCustomer = customerDAO.selectCustomerById(id);
        request.setAttribute("customer",existCustomer);
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/views/customer/sent.jsp");
        dis.forward(request,response);

    }

    public void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int salary = Integer.parseInt(request.getParameter("salary"));
        Customer newcustomer = new Customer(name, email, salary);
//        request.setAttribute("message","Customer information wasupdated");
        customerDAO.insertCustomer(newcustomer);
        response.sendRedirect("/customer");
    }

    public void sentSalary(HttpServletRequest request, HttpServletResponse response) throws  SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        int salary = Integer.parseInt(request.getParameter("salarysent"));
        int id2 = Integer.parseInt(request.getParameter("id2"));
        int salaryreceive = Integer.parseInt(request.getParameter("salary"));
        Customer Customersent = customerDAO.selectCustomerById(id);
        Customer Customerreceive = customerDAO.selectCustomerById(id2);
        Customersent.setSalary(salary - salaryreceive);
        Customerreceive.setSalary(Customerreceive.getSalary() + salaryreceive);
        customerDAO.isUpdateCustomer(Customersent,Customerreceive);
        response.sendRedirect("/customer");
    }

    public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.isDeleteCustomer(id);
//        List<Customer> customerList = customerDAO.selectAllCustomer();
//        request.setAttribute("customers",customerList);
        response.sendRedirect("/custommer");
    }
}
