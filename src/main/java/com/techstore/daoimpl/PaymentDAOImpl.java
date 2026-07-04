package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.techstore.dao.PaymentDAO;
import com.techstore.model.Payment;
import com.techstore.util.DBConnection;

public class PaymentDAOImpl implements PaymentDAO {

    private static final String INSERT_PAYMENT =
            "INSERT INTO payments(order_id, payment_method, transaction_id, amount, payment_status) VALUES(?,?,?,?,?)";

    @Override
    public boolean savePayment(Payment payment) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_PAYMENT);

            preparedStatement.setInt(1, payment.getOrderId());
            preparedStatement.setString(2, payment.getPaymentMethod());
            preparedStatement.setString(3, payment.getTransactionId());
            preparedStatement.setBigDecimal(4, payment.getAmount());
            preparedStatement.setString(5, payment.getPaymentStatus());

            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

}