package com.codegym.controller;

import com.codegym.model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListCustomerServlet", urlPatterns = "/ListCustomer")
public class ListCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<Customer>();

        customers.add(new Customer(1,"Mai Văn Hoàn", "1983-08-20", "Hà Nội", 1000000, ""));
        customers.add(new Customer(2,"Nguyễn Văn Nam", "1983-08-21", "Bắc Giang", 23000000, ""));
        customers.add(new Customer(3,"Nguyễn Thái Hóa", "1983-08-22", "Nam Định", 1200000, ""));
        customers.add(new Customer(4,"Trần Đăng Khoa", "1983-08-16", "Hà Tây", 4000000, ""));
        customers.add(new Customer(5,"Nguyễn Đình Thi", "1983-08-18", "Hà Nội", 1000000, ""));
//            PrintWriter writer = response.getWriter();
//            writer.println(customers);

        request.setAttribute("list", customers);

        RequestDispatcher rd = request.getRequestDispatcher("print.jsp");
        rd.forward(request, response);

    }
}
