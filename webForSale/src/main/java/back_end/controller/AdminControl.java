package back_end.controller;

import back_end.model.Account;
import back_end.model.Product;
import back_end.DAO.productDAO;
import back_end.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.AcceptPendingException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

@WebServlet(name = "AdminControl", urlPatterns = "/admin")
public class AdminControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter("submit");
        if (submit == null) {
            submit = "";
        }
        switch (submit) {
            case "Delete": {
                delete(request, response);
                break;
            }
            case "Save": {
                edit(request, response);
                break;
            }
            case "Add": {
                add(request, response);
                break;
            }
            default: {
                response.sendRedirect("/home");
                break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null) {
            account = new Account();
        }
        if (account.getIsAdmin() == 1) {
            showProduct(request, response);
        } else {
            response.sendRedirect("/home");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productDAO dao = new productDAO();
        String id = request.getParameter("id");
        boolean check = dao.deteleProductByID(id);
        request.setAttribute("mess", (check ? "Successful !" : "Something is wrong"));
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("views/ManagerProduct.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
        productDAO dao = new productDAO();
//        String name =
        String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String img = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        String description = new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
        int category = Integer.parseInt(request.getParameter("category"));
        Boolean check = dao.addProduct(name, img, price, title, description, category);
        request.setAttribute("mess", (check ? "Successful !" : "Something is wrong"));
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("views/ManagerProduct.jsp").forward(request, response);
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
        productDAO dao = new productDAO();

        String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
        String name =new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
        String img = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        String title =new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        String description =new String(request.getParameter("description").getBytes("ISO-8859-1"), "UTF-8");
        int category = Integer.parseInt(request.getParameter("category"));

        Boolean check = dao.editProduct(id, name, img, price, title, description, category);
        request.setAttribute("mess", (check ? "Successful !" : "Something is wrong"));
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("views/ManagerProduct.jsp").forward(request, response);
    }

    protected void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productDAO dao = new productDAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listC);
        request.getRequestDispatcher("views/ManagerProduct.jsp").forward(request, response);
    }
}
