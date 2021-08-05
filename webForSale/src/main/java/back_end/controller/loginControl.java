package back_end.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import back_end.model.Account;
import back_end.model.Category;
import back_end.model.Product;
import back_end.DAO.productDAO;


@WebServlet(name = "loginControl", urlPatterns = "/loginControl")
public class loginControl extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("submit");
        if(action == null){
            action="";
        }
        switch (action){
            case "LOGIN":
            {
                try {
                    login(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
            case "REGISTER":
            {
                try {
                    processRequest(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
            case "CHECK":
            {
                try {
                    forGotPassWord(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }

            default:
            {
                response.sendRedirect("home");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logOut(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        session.removeAttribute("acc");
        session.removeAttribute("cardList");
        response.sendRedirect("/home");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try{
            String username = request.getParameter("username");
            String pass = request.getParameter("password");
            productDAO dao = new productDAO();
            Account account = dao.login(username,pass);
            if (account==null){
                request.setAttribute("mess","Something is wrong !!!");
                request.getRequestDispatcher("/views/Login.jsp").forward(request,response);
            }else if(account.getStatus()==0){
                request.setAttribute("mess","This account is LOCKED !!!");
                request.getRequestDispatcher("/views/Login.jsp").forward(request,response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("acc",account);
                response.sendRedirect("home");
            }
        }catch (Exception e){

        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException, SQLException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        productDAO dao = new productDAO();
        boolean check = ((password.equals(repassword))&&(dao.checkNewAccount(username)));
        if (check){
            dao.addAccount(username,password,fullName,email);
            request.setAttribute("mess","Successful !");
            request.getRequestDispatcher("views/Register.jsp").forward(request,response);
        }else{
            request.setAttribute("mess","username is exist or password is wrong !");
            request.getRequestDispatcher("views/Register.jsp").forward(request,response);
        }

    }

    protected void forGotPassWord(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException, SQLException{
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        productDAO dao = new productDAO();
        String pass = dao.getPassWord(username,fullName,email);
        if (pass == null){
            request.setAttribute("mess","Something is wrong !");
        }else {
            request.setAttribute("mess","Your password is: "+ pass);
        }
        request.getRequestDispatcher("/views/getPassword.jsp").forward(request, response);
    }

}
