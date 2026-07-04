package com.techstore.service;
import java.util.List;

import com.techstore.dao.OrderDAO;
import com.techstore.daoimpl.OrderDAOImpl;
import com.techstore.model.Order;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService() {

        orderDAO = new OrderDAOImpl();

    }

    public int placeOrder(Order order) {

        return orderDAO.placeOrder(order);

    }
    
    public List<Order> getOrdersByUserId(int userId) {

        return orderDAO.getOrdersByUserId(userId);

    }

}