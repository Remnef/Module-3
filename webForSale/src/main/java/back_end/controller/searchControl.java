package back_end.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back_end.model.Category;
import back_end.model.Product;
import back_end.DAO.productDAO;

@WebServlet(name = "searchControl",urlPatterns = "/search")
public class searchControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        searchByName(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt");
        productDAO dao = new productDAO();
        List<Product> list = dao.getProductByName(txtSearch);
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("listP",list);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.setAttribute("txtS",txtSearch);
        request.getRequestDispatcher("views/Home.jsp").forward(request,response);
    }
}
