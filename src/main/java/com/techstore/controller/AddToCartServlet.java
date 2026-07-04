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

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        cartService = new CartService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Cart cart = gson.fromJson(
                    request.getReader(),
                    Cart.class);

            Cart existingCart = cartService.getCartItem(
                    cart.getUserId(),
                    cart.getProductId());

            if (existingCart != null) {

                int newQuantity =
                        existingCart.getQuantity() + cart.getQuantity();

                cartService.updateQuantity(
                        existingCart.getCartId(),
                        newQuantity);

            } else {

                cartService.addToCart(cart);

            }

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().write(
                    "{\"success\":true,\"message\":\"Product Added To Cart\"}");

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Unable To Add Product\"}");

        }

    }

}