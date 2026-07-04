package com.techstore.controller;

import java.io.IOException;

import com.techstore.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductService productService;

    @Override
    public void init() throws ServletException {

        productService = new ProductService();

    }

    @Override
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            int productId = Integer.parseInt(request.getParameter("id"));

            boolean success = productService.deleteProduct(productId);

            if (success) {

                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(
                        "{\"success\":true,\"message\":\"Product Deleted Successfully\"}");

            } else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                response.getWriter().write(
                        "{\"success\":false,\"message\":\"Unable to Delete Product\"}");

            }

        } catch (Exception e) {

            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Server Error\"}");

        }

    }

}