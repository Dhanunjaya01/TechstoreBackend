package com.techstore.service;

import com.techstore.dao.PaymentDAO;
import com.techstore.daoimpl.PaymentDAOImpl;
import com.techstore.model.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService() {

        paymentDAO = new PaymentDAOImpl();

    }

    public boolean savePayment(Payment payment) {

        return paymentDAO.savePayment(payment);

    }

}