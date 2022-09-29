package com.example.carservice.service.order;

import com.example.carservice.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();
    public void saveOrder(Order order);
    public Order getOrder(long id);
    public void deleteOrder(long id);
}
