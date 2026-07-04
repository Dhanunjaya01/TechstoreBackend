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

@WebServlet("/product")
public class GetProductByIdServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        productService = new ProductService();
        gson = new Gson();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int productId = Integer.parseInt(request.getParameter("id"));

            Product product = productService.getProductById(productId);

            if (product != null) {

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(gson.toJson(product));

            } else {

                response.setStatus(HttpServletResponse.SC_NOT_FOUND);

                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Product Not Found\"}");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Server Error\"}");

        }

    }

}