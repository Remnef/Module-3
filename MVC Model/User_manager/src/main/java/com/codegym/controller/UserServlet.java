package com.codegym.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.codegym.dao.UserDao;

import com.codegym.model.User;

@WebServlet(name = "UserServlet", urlPatterns = "/User")
public class UserServlet extends javax.servlet.http.HttpServlet {
    UserDao userDao = new UserDao();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "create": {
                showCreateForm(request, response);
                break;
            }
            case "edit": {
//                showEditForm(request, response);
                break;
            }
            case "delete": {
//                showDeleteForm(request, response);
                break;
            }
            case "view": {
//                viewUser(request, response);
                break;
            }
            default:
                listUser(request, response);
                break;
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
            {
                showCreateForm(request, response);
                break;
            }
            case "edit":
            {
//                showEditForm(request, response);
                break;
            }
            case "delete":
            {
//                showDeleteForm(request,response);
                break;
            }
            case "view":
            {
//                viewUser(request, response);
                break;
            }
            default:
                listUser(request,response);
                break;
        }
    }
    private void listUser(HttpServletRequest request, HttpServletResponse response){
        List<User> users = userDao.selectAllUsers();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response){

    }
}
