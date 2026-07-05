package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.User;
import com.techstore.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        userService = new UserService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	 response.setHeader("Access-Control-Allow-Origin", "https://techstore-store.vercel.app");
    	 response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	 response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            User loginRequest = gson.fromJson(
                    request.getReader(),
                    User.class);

            User user = userService.loginUser(
                    loginRequest.getEmail(),
                    loginRequest.getPassword());

            if (user != null) {

                HttpSession session = request.getSession();

                session.setAttribute("user", user);

                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(

                        "{"
                        + "\"success\":true,"
                        + "\"message\":\"Login Successful\","
                        + "\"user\":{"
                        + "\"userId\":" + user.getUserId() + ","
                        + "\"firstName\":\"" + user.getFirstName() + "\","
                        + "\"lastName\":\"" + user.getLastName() + "\","
                        + "\"email\":\"" + user.getEmail() + "\""
                        + "}"
                        + "}"

                );

            } else {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                response.getWriter().write(

                        "{"
                        + "\"success\":false,"
                        + "\"message\":\"Invalid Email or Password\""
                        + "}"

                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(

                    "{"
                    + "\"success\":false,"
                    + "\"message\":\"Server Error\""
                    + "}"

            );

        }

    }
    @Override
    protected void doOptions(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "https://techstore-store.vercel.app");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}