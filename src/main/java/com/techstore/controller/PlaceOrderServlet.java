package com.techstore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import com.google.gson.Gson;
import com.techstore.model.Order;
import com.techstore.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        orderService = new OrderService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Order order = gson.fromJson(
                    request.getReader(),
                    Order.class);

            order.setOrderNumber(
                    "ORD-" + UUID.randomUUID().toString().substring(0, 8));

            order.setOrderStatus("Order Placed");

            order.setPaymentStatus("Pending");

            int orderId = orderService.placeOrder(order);

            if (orderId > 0) {

                response.getWriter().write(

                        "{"
                        + "\"success\":true,"
                        + "\"orderId\":" + orderId
                        + "}"

                );

            } else {

                response.getWriter().write(

                        "{"
                        + "\"success\":false,"
                        + "\"message\":\"Unable To Place Order\""
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

}