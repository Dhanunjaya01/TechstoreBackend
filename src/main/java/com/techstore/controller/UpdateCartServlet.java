package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.Cart;
import com.techstore.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        cartService = new CartService();
        gson = new Gson();

    }

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Cart cart = gson.fromJson(
                    request.getReader(),
                    Cart.class);

            boolean success = cartService.updateQuantity(
                    cart.getCartId(),
                    cart.getQuantity());

            if (success) {

                response.getWriter().write(

                        "{\"success\":true,\"message\":\"Cart Updated\"}"

                );

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(

                        "{\"success\":false,\"message\":\"Update Failed\"}"

                );

            }

        }

        catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(

                    "{\"success\":false,\"message\":\"Server Error\"}"

            );

        }

    }

}