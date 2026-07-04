package com.techstore.dao;

import com.techstore.model.Payment;

public interface PaymentDAO {

    boolean savePayment(Payment payment);

}