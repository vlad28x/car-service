package com.example.carservice.service.orderStatus;

import com.example.carservice.entity.OrderStatus;
import com.example.carservice.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    OrderStatusRepository orderStatusRepository;


    @Override
    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    @Override
    public void saveOrderStatus(OrderStatus orderStatus) {
      orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus getOrderStatus(long id) {
        OrderStatus orderStatus = new OrderStatus();
        Optional<OrderStatus> optional = orderStatusRepository.findById(id);
        if (optional.isPresent()) {
            orderStatus = optional.get();
        }
        return orderStatus;
    }

    @Override
    public void deleteOrderStatus(long id) {
        orderStatusRepository.deleteById(id);
    }
}
