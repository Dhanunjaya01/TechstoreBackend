package com.techstore.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.techstore.model.Product;
import com.techstore.service.ProductService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class GetAllProductsServlet extends HttpServlet {

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
    	
    	response.setHeader("Access-Control-Allow-Origin", "https://techstore-store.vercel.app");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            List<Product> products =
                    productService.getAllProducts();

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().write(
                    gson.toJson(products));

        }

        catch (Exception e) {

            e.printStackTrace();

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            response.getWriter().write(

                    "{"
                    + "\"success\":false,"
                    + "\"message\":\"Unable to Fetch Products\""
                    + "}"

            );

        }

    }
    @Override
    protected void doOptions(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", "https://techstore-store.vercel.app");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}