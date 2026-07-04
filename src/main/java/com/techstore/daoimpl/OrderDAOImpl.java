package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.techstore.dao.OrderDAO;
import com.techstore.model.Order;
import com.techstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER =
            "INSERT INTO orders (user_id, order_number, total_amount, order_status, payment_status, address_id) VALUES (?,?,?,?,?,?)";
    private static final String GET_USER_ORDERS =
            "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";

    @Override
    public int placeOrder(Order order) {

        int orderId = 0;

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            INSERT_ORDER,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getOrderNumber());
            preparedStatement.setBigDecimal(3, order.getTotalAmount());
            preparedStatement.setString(4, order.getOrderStatus());
            preparedStatement.setString(5, order.getPaymentStatus());
            preparedStatement.setInt(6, order.getAddressId());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {

                ResultSet resultSet =
                        preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {

                    orderId = resultSet.getInt(1);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return orderId;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {

        List<Order> orders = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(GET_USER_ORDERS);

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Order order = new Order();

                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setOrderStatus(rs.getString("order_status"));
                order.setPaymentStatus(rs.getString("payment_status"));
                order.setAddressId(rs.getInt("address_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));

                orders.add(order);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return orders;

    }

}