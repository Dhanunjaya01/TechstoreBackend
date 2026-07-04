package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.Admin;
import com.techstore.service.AdminService;
import com.techstore.util.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminService adminService;

    @Override
    public void init() throws ServletException {

        adminService = new AdminService();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        Gson gson = new Gson();

        Admin loginAdmin = gson.fromJson(
                request.getReader(),
                Admin.class);

        Admin admin = adminService.loginAdmin(
                loginAdmin.getEmail(),
                loginAdmin.getPassword());

        ApiResponse apiResponse;

        if (admin != null) {

            HttpSession session = request.getSession();

            session.setAttribute("admin", admin);

            apiResponse = new ApiResponse(
                    true,
                    "Admin Login Successful",
                    admin);

        } else {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            apiResponse = new ApiResponse(
                    false,
                    "Invalid Admin Email or Password",
                    null);

        }

        response.getWriter().write(
                gson.toJson(apiResponse));

    }

}