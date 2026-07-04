package com.techstore.dao;

import java.util.List;
import com.techstore.model.OrderItem;

public interface OrderItemDAO {

    boolean addOrderItems(List<OrderItem> orderItems);
    List<OrderItem> getOrderItems(int orderId);

}