package com.techstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.techstore.dao.OrderItemDAO;
import com.techstore.model.OrderItem;
import com.techstore.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

	private static final String GET_ORDER_ITEMS =
			"SELECT oi.*, p.product_name, p.image_url " +
			"FROM order_items oi " +
			"JOIN products p ON oi.product_id = p.product_id " +
			"WHERE oi.order_id = ?";
    private static final String INSERT_ORDER_ITEM =
            "INSERT INTO order_items(order_id, product_id, quantity, price, subtotal) VALUES (?,?,?,?,?)";

    @Override
    public boolean addOrderItems(List<OrderItem> orderItems) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_ORDER_ITEM);

            for (OrderItem item : orderItems) {

                preparedStatement.setInt(1, item.getOrderId());
                preparedStatement.setInt(2, item.getProductId());
                preparedStatement.setInt(3, item.getQuantity());
                preparedStatement.setBigDecimal(4, item.getPrice());
                preparedStatement.setBigDecimal(5, item.getSubtotal());

                preparedStatement.addBatch();

            }

            int[] rows = preparedStatement.executeBatch();

            return rows.length > 0;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId) {

        List<OrderItem> items = new ArrayList<>();

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(GET_ORDER_ITEMS);

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                OrderItem item = new OrderItem();

                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));

                item.setProductName(rs.getString("product_name"));
                item.setImageUrl(rs.getString("image_url"));

                items.add(item);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return items;

    }

}