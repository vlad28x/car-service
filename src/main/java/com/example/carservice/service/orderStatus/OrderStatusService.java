package com.example.carservice.service.orderStatus;

import com.example.carservice.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    public List<OrderStatus> getAllOrderStatuses();
    public void saveOrderStatus(OrderStatus orderStatus);
    public OrderStatus getOrderStatus(long id);
    public void deleteOrderStatus(long id);
}
