package com.techstore.controller;

import java.io.IOException;
import java.util.Date;

import com.google.gson.Gson;
import com.techstore.dao.UserDAO;
import com.techstore.daoimpl.UserDAOImpl;
import com.techstore.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        userDAO = new UserDAOImpl();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            // Convert JSON request into User object
            User user = gson.fromJson(request.getReader(), User.class);

            // Default values
            user.setProfileImage("default.png");
            user.setAccountStatus("ACTIVE");
            user.setEmailVerified(true);

            // If date of birth is not provided
            if (user.getDateOfBirth() == null) {
                user.setDateOfBirth(new Date());
            }

            boolean success = userDAO.registerUser(user);

            if (success) {

                response.setStatus(HttpServletResponse.SC_CREATED);

                response.getWriter().write(
                        "{"
                        + "\"success\":true,"
                        + "\"message\":\"Registration Successful\""
                        + "}"
                );

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(
                        "{"
                        + "\"success\":false,"
                        + "\"message\":\"Registration Failed\""
                        + "}"
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{"
                    + "\"success\":false,"
                    + "\"message\":\"" + e.getMessage() + "\""
                    + "}"
            );

        }

    }

}