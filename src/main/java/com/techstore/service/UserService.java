package com.techstore.service;

import java.util.List;

import com.techstore.dao.UserDAO;
import com.techstore.daoimpl.UserDAOImpl;
import com.techstore.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    public boolean registerUser(User user) {

        if (userDAO.emailExists(user.getEmail())) {
            return false;
        }

        return userDAO.registerUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

}