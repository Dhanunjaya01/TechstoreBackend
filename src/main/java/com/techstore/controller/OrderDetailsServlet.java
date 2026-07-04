package com.techstore.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.techstore.model.OrderItem;
import com.techstore.service.OrderItemService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderItemService orderItemService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        orderItemService = new OrderItemService();
        gson = new Gson();

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int orderId =
                    Integer.parseInt(request.getParameter("orderId"));

            List<OrderItem> items =
                    orderItemService.getOrderItems(orderId);

            response.getWriter().write(
                    gson.toJson(items));

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

    }

}