package back_end.controller;

import back_end.model.Account;
import back_end.model.Category;
import back_end.model.Product;
import back_end.DAO.productDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InfoControl",urlPatterns = "/info")
public class InfoControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            account = new Account();
        }
        if (account.getStatus() == 1) {
            String submit = request.getParameter("submit");
            switch (submit){
                case "changePass":
                {
                    try {
                        changePass(request,response);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                }
                default:
                {
                    response.sendRedirect("/home");
                    break;
                }
            }
        } else {
            response.sendRedirect("/home");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            account = new Account();
        }
        if (account.getIsAdmin() == 1) {
            showInfo(request, response, account);
        } else {
            response.sendRedirect("/home");
        }
    }

    protected void showInfo(HttpServletRequest request, HttpServletResponse response, Account account) throws ServletException, IOException {
        productDAO dao = new productDAO();
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("account",account);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.getRequestDispatcher("views/accountInfo.jsp").forward(request,response);
    }

    protected void changePass(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String pass = request.getParameter("password");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        String username = account.getUsername();
        productDAO dao = new productDAO();
        Account accountcheck = dao.login(username,pass);
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        if(accountcheck == null){
            request.setAttribute("mess","Password false");;
        }else{
            String newPass = request.getParameter("newPassword");
            String retype = request.getParameter("reTypePassword");
            if(newPass.equals(retype)){
                Boolean check = dao.changePass(newPass,account.getId());
                if (check){
                    request.setAttribute("mess","Successful !");
                }else{
                    request.setAttribute("mess","something is wrong !");
                }
            }else{
                request.setAttribute("mess","new password and retypePass is not equal");
            }
        }
        request.setAttribute("account",account);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.getRequestDispatcher("views/accountInfo.jsp").forward(request,response);
    }
}
