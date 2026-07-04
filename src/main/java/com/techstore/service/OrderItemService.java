package com.techstore.service;

import java.util.List;

import com.techstore.dao.OrderItemDAO;
import com.techstore.daoimpl.OrderItemDAOImpl;
import com.techstore.model.OrderItem;

public class OrderItemService {

    private OrderItemDAO orderItemDAO;

    public OrderItemService() {

        orderItemDAO = new OrderItemDAOImpl();

    }

    public boolean addOrderItems(List<OrderItem> orderItems) {

        return orderItemDAO.addOrderItems(orderItems);

    }
    public List<OrderItem> getOrderItems(int orderId) {

        return orderItemDAO.getOrderItems(orderId);

    }

}