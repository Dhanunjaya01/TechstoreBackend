package com.techstore.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.techstore.model.Address;
import com.techstore.service.AddressService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveAddress")
public class AddressServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AddressService addressService;
    private Gson gson;

    @Override
    public void init() throws ServletException {

        addressService = new AddressService();
        gson = new Gson();

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            Address address =
                    gson.fromJson(request.getReader(), Address.class);

            int addressId =
                    addressService.saveAddress(address);

            if (addressId > 0) {

                response.getWriter().write(

                        "{"
                        + "\"success\":true,"
                        + "\"addressId\":" + addressId
                        + "}"

                );

            } else {

                response.getWriter().write(

                        "{"
                        + "\"success\":false,"
                        + "\"message\":\"Unable To Save Address\""
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