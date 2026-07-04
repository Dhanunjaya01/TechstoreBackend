package com.techstore.controller;

import java.io.IOException;
import java.util.UUID;

import com.google.gson.Gson;
import com.techstore.model.Payment;
import com.techstore.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/savePayment")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PaymentService paymentService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        paymentService = new PaymentService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Payment payment =
                    gson.fromJson(request.getReader(), Payment.class);

            payment.setTransactionId(
                    "TXN-" + UUID.randomUUID().toString().substring(0, 8));

            boolean saved =
                    paymentService.savePayment(payment);

            if (saved) {

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