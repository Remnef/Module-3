package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements IUserDao{
    private String jdbcURL = "jdbc:mysql://localhost:3306/users?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Remnef131299";

    private static final String INSERT_USERS_SQL = "INSERT INTO user" + "  (name, email, address, gender) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id,name,email,address,gender from user where id =?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS_SQL = "delete from user where id = ?;";
    private static final String UPDATE_USERS_SQL = "update user set `name` = ?,email= ?, address =?, gender =? where id = ?;";

    public UserDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }



    @Override
    public void insertUser(User user) throws SQLException {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
        ){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setBoolean(4,user.getGender());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
        ){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = Boolean.parseBoolean(String.valueOf(rs.getInt("gender")));
                user = new User(id, name, email, address, gender);
            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return user ;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            System.out.println(preparedStatement);
            ResultSet  rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                Boolean gender = Boolean.parseBoolean(String.valueOf(rs.getInt("gender")));
                users.add(new User(id,name,email,address,gender ));
            }
        }catch(SQLException e){
            printSQLException(e);
        }
        return  users;
    }

    @Override
    public boolean deleleUser(int id) throws SQLException {
        boolean rowDelete;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);){
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() >0;
        }
        return rowDelete;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdate;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL
            )){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setBoolean(4,user.getGender());
            preparedStatement.setInt(5,user.getId());
            rowUpdate = preparedStatement.executeUpdate()>0;
        }
        return rowUpdate;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
