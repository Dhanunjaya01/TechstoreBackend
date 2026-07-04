package com.techstore.dao;
import java.util.List;
import com.techstore.model.Order;

public interface OrderDAO {

    int placeOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}