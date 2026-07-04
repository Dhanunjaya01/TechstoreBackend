package com.techstore.controller;

import java.io.IOException;

import com.techstore.service.CartService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartService cartService;

    @Override
    public void init() throws ServletException {

        cartService = new CartService();

    }

    @Override
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int cartId = Integer.parseInt(
                    request.getParameter("cartId"));

            boolean success =
                    cartService.removeFromCart(cartId);

            if (success) {

                response.getWriter().write(

                        "{\"success\":true,"
                        + "\"message\":\"Item Removed Successfully\"}"

                );

            } else {

                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(

                        "{\"success\":false,"
                        + "\"message\":\"Unable To Remove Item\"}"

                );

            }

        }

        catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(

                    "{\"success\":false,"
                    + "\"message\":\"Server Error\"}"

            );

        }

    }

}