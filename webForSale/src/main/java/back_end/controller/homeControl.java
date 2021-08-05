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

@WebServlet(name = "homeControl",urlPatterns = "/home")
public class homeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            processRequest(request,response);
        }else{
            getAllCateID(request,response);
        }

//        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException{
        productDAO dao = new productDAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("listP",list);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.getRequestDispatcher("views/Home.jsp").forward(request,response);
    }

    protected void getAllCateID(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        String id = request.getParameter("action");
        productDAO dao = new productDAO();
        List<Product> list = dao.getAllcateID(id);
        request.setAttribute("listP",list);
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.setAttribute("tag",id);
        request.getRequestDispatcher("views/Home.jsp").forward(request,response);
    }
}
