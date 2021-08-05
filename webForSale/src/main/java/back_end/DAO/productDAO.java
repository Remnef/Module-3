package back_end.DAO;


import back_end.Contact.MySQLConnUtils;
import back_end.model.*;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class productDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        String query ="SELECT * FROM sneakers order by id desc";
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        }catch (Exception e){

        }
        return products;
    }

    public boolean setLock(int id){
        boolean check = false;
        try{
            String query="UPDATE `account` SET `status` = ? WHERE (`id` = ?);";
            productDAO dao = new productDAO();
            int status = 0;
            List<Account> list = dao.accountList();
            for (Account account: list) {
                if (account.getId() == id){
                    status = ((account.getStatus()==1)?0:1);
                }
            }
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,status);
            ps.setInt(2,id);
            check = ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return check;
    }

    public boolean setMod(int id){
        boolean check = false;
        try{
            String query="UPDATE `account` SET `isUser` = ? WHERE (`id` = ?);";
            productDAO dao = new productDAO();
            int isUser = 0;
            List<Account> list = dao.accountList();
            for (Account account: list) {
                if (account.getId() == id){
                    isUser = (account.getIsUser()==1)?0:1;
                }
            }
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,isUser);
            ps.setInt(2,id);
            check = ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return check;
    }


    public List<Category> getAllCategory(){
        List<Category> list = new ArrayList<>();
        String query ="SELECT * FROM category order by id asc";
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        }catch (Exception e){

        }
        return list;
    }

    public Product getLast(){
        String query = "SELECT *  \n" +
                "FROM sneakers  \n" +
                "ORDER BY id desc\n" +
                "LIMIT 1 ";
        Product product = null;
        try{
            conn = new MySQLConnUtils().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        }catch (Exception e){}
        return product;
    }

    public boolean getBill(saleCard card){
        boolean check = false;
        try{
            String query="INSERT INTO `salelist` (`name`, `price`, `quantity`, `bill`, `time`) VALUES (?, ?, ?, ?,?);";
            conn = MySQLConnUtils.getConnection();
            String timeStamp = new SimpleDateFormat("HH:mm dd/MM/YYYY").format(new Date());
            ps = conn.prepareStatement(query);
            ps.setString(1,card.getProduct().getName());
            ps.setFloat(2, (float) card.getPrice());
            ps.setInt(3,card.getQuantity());
            ps.setFloat(4,(float) card.getPrice()*card.getQuantity());
            ps.setString(5,timeStamp);
            check = ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return check;
    }

    public List<Bill> getBillList() throws SQLException {
        List<Bill> billList = new ArrayList<>();
        String query ="SELECT * FROM salelist order by id desc";
        conn = MySQLConnUtils.getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while(rs.next()){
            billList.add(new Bill(rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getInt(4),
                    rs.getFloat(5),
                    rs.getString(6)));
        }
        return billList;
    }
    public Product getProductByID(String id){
        String query = "SELECT * FROM sneakers.sneakers where id =?";
        Product product = null;
        try{
            conn = new MySQLConnUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        }catch (Exception e){}
        return product;
    }

    public List<Product> getAllcateID(String id){
        List<Product> products = new ArrayList<>();
        String query ="select * from sneakers where cateID = ?";
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        }catch (Exception e){

        }
        return products;
    }

    public List<Product> getProductByName(String txt){
        List<Product> products = new ArrayList<>();
        String query ="SELECT * FROM sneakers.sneakers where `name` like ?";
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,"%"+txt+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                products.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        }catch (Exception e){

        }
        return products;
    }

    public boolean changePass(String pass,int id){
        boolean check = false;
        try{
            String query="UPDATE `sneakers`.`account` SET `pass` = ? WHERE (`id` = ?);";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,pass);
            ps.setInt(2,id);
            check = ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return check;
    }

    public Account login(String username, String pass) throws SQLException {
        String query = "SELECT * FROM `account` where username = ? and pass = ?";
        Account account = null;
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            rs = ps.executeQuery();
            while(rs.next()){
                account = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8));
            }
        }catch(Exception e){

        }
        return account;
    }

    public boolean checkNewAccount(String newUsername){
        boolean check =true;
        Account account = null;
        try {
            String query ="SELECT * FROM sneakers.account where username = ?;";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,newUsername);
            rs = ps.executeQuery();
            while(rs.next()){
                account = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8));
            }
            check = !(newUsername.equals(account.getUsername()));
        }catch (Exception e){

        }
        return check;
    }

    public void addAccount(String username, String pass, String name, String email){
        try{
            String query ="insert into `account` (username, pass, isUser, isAdmin, `name`, email, `status`)\n" +
                    "value(?,?,1,0,?,?,1)";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            ps.setString(3,name);
            ps.setString(4,email);
            ps.executeUpdate();
        }catch (Exception e){

        }
    }

    public void addAccountAdmin(String username, String pass,String isUser, String name, String email){
        try{
            String query ="insert into `account` (username, pass, isUser, isAdmin, `name`, email, `status`)\n" +
                    "value(?,?,?,0,?,?,1)";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,pass);
            ps.setString(3,isUser);
            ps.setString(4,name);
            ps.setString(5,email);
            ps.executeUpdate();
        }catch (Exception e){

        }
    }

    public boolean addProduct(String name, String img, double price, String title, String description, int cateID){
        boolean check = false;
        try{
            String query ="insert into `sneakers` (`name`, image, price, title, description, cateID, saleID ) values(?,?,?,?,?,?,1)";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,img);
            ps.setDouble(3,price);
            ps.setString(4,title);
            ps.setString(5,description);
            ps.setInt(6,cateID);
            check = ps.executeUpdate() >0;
        }catch (Exception e){

        }
        return check;
    }

    public boolean editProduct(String id, String name, String img, double price, String title, String description, int cateID){
        boolean check = false;
        try{
            String query ="UPDATE `sneakers` SET `name` = ?, `image` = ?, `price` = ?, `title` = ?, `description` = ?, `cateID` = ?, `saleID` = '1' WHERE (`id` = ?);";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,img);
            ps.setDouble(3,price);
            ps.setString(4,title);
            ps.setString(5,description);
            ps.setInt(6,cateID);
            ps.setString(7,id);
            check = ps.executeUpdate() >0;
        }catch (Exception e){

        }
        return check;
    }

    public List<Account> accountList(){
        List<Account> accounts = new ArrayList<>();
        String query ="SELECT * FROM account";
        try{
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                accounts.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)));
            }
        }catch (Exception e){

        }
        return accounts;
    }

    public String getPassWord(String username, String name, String email){
        productDAO dao = new productDAO();
        String pass = null;
        List<Account> accounts = dao.accountList();
        for(Account account: accounts){
            Boolean check = true;
            if (!(username.equals(account.getUsername()))) {
                check = false;
            }
            if (!(name.equals(account.getName()))) {
                check = false;
            }
            if (!(email.equals(account.getEmail()))) {
                check = false;
            }
            if (check){
                pass = account.getPassword();
            }
        }
        return pass;
    }

    public boolean deteleProductByID(String id){
        boolean check = true;
        try{
            String query = "delete from sneakers where id = ?";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,id);
            check= ps.executeUpdate() > 0;
        }catch (Exception e){

        }
        return check;
    }

    public static void main(String[] args) throws SQLException {
       productDAO dao = new productDAO();
//       List<Category> list = dao.getAllCategory();
//        System.out.println(list);
       Account account = dao.login("remnef","131299");
        System.out.println(account);

    }


}
