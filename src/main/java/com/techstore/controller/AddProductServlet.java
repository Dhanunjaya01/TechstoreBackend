package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.Product;
import com.techstore.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        productService = new ProductService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Product product = gson.fromJson(
                    request.getReader(),
                    Product.class);

            boolean success = productService.addProduct(product);

            if (success) {

                response.setStatus(HttpServletResponse.SC_CREATED);

                response.getWriter().write(
                        "{"
                        + "\"success\":true,"
                        + "\"message\":\"Product Added Successfully\""
                        + "}");

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(
                        "{"
                        + "\"success\":false,"
                        + "\"message\":\"Failed to Add Product\""
                        + "}");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{"
                    + "\"success\":false,"
                    + "\"message\":\"Server Error\""
                    + "}");

        }

    }

}