package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.CheckoutRequest;
import com.techstore.service.CheckoutService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CheckoutService checkoutService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        checkoutService = new CheckoutService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            CheckoutRequest checkoutRequest =
                    gson.fromJson(request.getReader(),
                            CheckoutRequest.class);

            boolean success =
                    checkoutService.checkout(checkoutRequest);

            if (success) {

                response.getWriter().write(
                        "{\"success\":true}"
                );

            } else {

                response.getWriter().write(
                        "{\"success\":false}"
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Server Error\"}"
            );

        }

    }

}