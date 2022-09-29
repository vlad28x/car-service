package com.example.carservice.service.order;

import com.example.carservice.entity.Order;
import com.example.carservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void saveOrder(Order order) {
    orderRepository.save(order);
    }

    @Override
    public Order getOrder(long id) {
        Order order = new Order();
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            order = optional.get();
        }
        return order;
    }

    @Override
    public void deleteOrder(long id) {
    orderRepository.deleteById(id);
    }
}
