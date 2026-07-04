package com.techstore.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.techstore.model.CartItem;
import com.techstore.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cart")
public class GetCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        cartService = new CartService();
        gson = new Gson();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int userId =
                    Integer.parseInt(request.getParameter("userId"));
            List<CartItem> cartList =
                    cartService.getCartItems(userId);
            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().write(
                    gson.toJson(cartList));

        }

        catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(

                    "{"
                    + "\"success\":false,"
                    + "\"message\":\"Unable To Fetch Cart\""
                    + "}"

            );

        }

    }

}