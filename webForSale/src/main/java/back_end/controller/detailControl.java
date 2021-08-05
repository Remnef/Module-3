package back_end.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back_end.DAO.productDAO;
import back_end.model.Category;
import back_end.model.Product;

@WebServlet(name = "detail", urlPatterns = "/detail")
public class detailControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("pID");
        productDAO dao = new productDAO();
        Product product = dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("prod",product);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.setAttribute("tag",id);
        request.getRequestDispatcher("views/Detail.jsp").forward(request,response);
    }
}
