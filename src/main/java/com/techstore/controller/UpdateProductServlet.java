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

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        productService = new ProductService();
        gson = new Gson();

    }

    @Override
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Product product = gson.fromJson(
                    request.getReader(),
                    Product.class);

            boolean success = productService.updateProduct(product);

            if (success) {

                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(
                        "{\"success\":true,\"message\":\"Product Updated Successfully\"}");

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Product Update Failed\"}");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Server Error\"}");

        }

    }

}