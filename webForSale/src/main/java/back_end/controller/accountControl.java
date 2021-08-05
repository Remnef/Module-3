package back_end.controller;

import back_end.model.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import back_end.DAO.productDAO;

@WebServlet(name = "accountControl",urlPatterns="/account")
public class accountControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            account = new Account();
        }
        if (account.getIsAdmin()==1 || account.getIsUser()==1) {
            String submit = request.getParameter("submit");
            if (submit==null){
                submit="";
            }
            switch (submit){
                case "Set Lock":
                {
                    lockSystem(request,response);
                    break;
                }
                case "Add":
                {
                    addAcount(request,response);
                    break;
                }
                case "Set mod":
                {
                    setMod(request,response);
                    break;
                }
                default:{
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
        if (account.getIsAdmin() == 1 || account.getIsUser()==1) {
            showAccount(request, response);
        } else {
            response.sendRedirect("/home");
        }
    }

    protected void setMod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        productDAO dao = new productDAO();
        boolean check = dao.setMod(Integer.parseInt(id));
        List<Account> accounts = dao.accountList();
        String mess = (check)?"Successful !":"Something is wrong !";
        request.setAttribute("mess", mess);
        request.setAttribute("listP",accounts);
        request.getRequestDispatcher("views/ManagerAccount.jsp").forward(request,response);
    }

    protected void showAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productDAO dao = new productDAO();
        List<Account> accounts = dao.accountList();
        request.setAttribute("listP",accounts);
        request.getRequestDispatcher("views/ManagerAccount.jsp").forward(request,response);
    }

    protected void lockSystem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        productDAO dao = new productDAO();
        boolean check = dao.setLock(Integer.parseInt(id));
        List<Account> accounts = dao.accountList();
        String mess = (check)?"Successful !":"Something is wrong !";
        request.setAttribute("mess", mess);
        request.setAttribute("listP",accounts);
        request.getRequestDispatcher("views/ManagerAccount.jsp").forward(request,response);
    }

    protected void addAcount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String isUser = request.getParameter("isUser");
        productDAO dao = new productDAO();
        boolean check = ((password.equals(repassword)) && (dao.checkNewAccount(username)));
        if (check) {
            dao.addAccountAdmin(username, password,isUser, fullName, email);
            List<Account> accounts = dao.accountList();
            request.setAttribute("mess", "Successful !");
            request.setAttribute("listP",accounts);
            request.getRequestDispatcher("views/ManagerAccount.jsp").forward(request,response);
        } else {
            List<Account> accounts = dao.accountList();
            request.setAttribute("mess", "username is exist or password is wrong !");
            request.setAttribute("listP",accounts);
            request.getRequestDispatcher("views/ManagerAccount.jsp").forward(request,response);;
        }
    }
}
