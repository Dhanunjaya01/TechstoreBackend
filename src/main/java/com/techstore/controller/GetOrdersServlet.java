package com.techstore.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.techstore.model.Order;
import com.techstore.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orders")
public class GetOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderService orderService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        orderService = new OrderService();
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

            List<Order> orders =
                    orderService.getOrdersByUserId(userId);

            response.getWriter().write(
                    gson.toJson(orders));

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

    }

}