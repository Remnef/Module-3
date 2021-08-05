package back_end.controller;

import back_end.Contact.MySQLConnUtils;
import back_end.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import back_end.DAO.productDAO;
@WebServlet(name = "saleControl", urlPatterns = "/sale")
public class saleControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            String submit = request.getParameter("submit");
            if (submit == null){
                submit = "";
            }
            switch (submit){
                case "addCard":
                {
                    addCard(request,response);
                    break;
                }
                case "Delete":
                {
                    deleteCard(request,response);
                    break;
                }
                case "Sale":
                {
                    sale(request,response);
                    break;
                }
                default:{
                    response.sendRedirect("/home");
                    break;
                }
            }
        }else{
            response.sendRedirect("/home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account != null) {
            String action = request.getParameter("action");
            if (action == null){
                action = "";
            }
            switch (action){
                case "show":
                {
                    showCardList(request,response);
                    break;
                }
                case "showAdmin":
                {
                    showAdmin(request,response);
                    break;
                }
                default:{
                    response.sendRedirect("/home");
                    break;
                }
            }
        }else{
            response.sendRedirect("/home");
        }
    }
    protected void sale(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        HttpSession session = request.getSession();
        List<saleCard> cardList = (List<saleCard>) session.getAttribute("cardList");
        productDAO dao = new productDAO();
        for(saleCard card : cardList) {
            dao.getBill(card);
        }
        session.removeAttribute("cardList");
        request.setAttribute("mess","Successful !");
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("listP",list);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.getRequestDispatcher("views/Home.jsp").forward(request,response);
    }

    protected void showCardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<saleCard> cardList = (List<saleCard>) session.getAttribute("cardList");
        if (cardList == null){
            cardList = new ArrayList<saleCard>();
        }
        double bill = 0;
        for (saleCard card: cardList) {
            bill+=card.getPrice()*card.getQuantity();
        }
        double vat = bill/10;
        double allbill = bill+vat;
        request.setAttribute("bill",bill);
        request.setAttribute("vat",vat);
        request.setAttribute("AllBill",allbill);
        request.getRequestDispatcher("views/Cart.jsp").forward(request,response);
    }

    protected void showAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        productDAO dao = new productDAO();
        try {
            List<Bill> billList = dao.getBillList();
            request.setAttribute("listP",billList);
            request.getRequestDispatcher("views/saleList.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void addCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        productDAO dao = new productDAO();
        if (quantity <1) {
            request.setAttribute("mess","Something is wrong !");
        }else{
            Product product = dao.getProductByID(id);
            HttpSession session = request.getSession();
            List<saleCard> cardList = (List<saleCard>) session.getAttribute("cardList");
            if (cardList == null){
                cardList = new ArrayList<saleCard>();
            }
            saleCard saleCard = new saleCard(cardList.size(),product,product.getPrice(),quantity);
            cardList.add(saleCard);
            session.setAttribute("cardList",cardList);
            request.setAttribute("mess","Successful !");
        }
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product lastP = dao.getLast();
        request.setAttribute("listP",list);
        request.setAttribute("listC",listC);
        request.setAttribute("lastP",lastP);
        request.getRequestDispatcher("views/Home.jsp").forward(request,response);
    }

    protected void deleteCard(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            HttpSession session = request.getSession();
            productDAO dao = new productDAO();
            List<saleCard> cardList = (List<saleCard>) session.getAttribute("cardList");
            cardList.remove(id);
            session.setAttribute("cardList",cardList);
            request.setAttribute("mess","Successful !");
            showCardList(request,response);
        }catch(Exception e){

        }

    }
}
