package com.codegym.dao;

import com.codegym.model.User;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLDataException, SQLException;
    public User selectUser(int id);
    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;
}
