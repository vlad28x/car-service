package com.aston.carservice.service.impl;

import com.aston.carservice.dto.OrderRequestDto;
import com.aston.carservice.dto.OrderResponseDto;
import com.aston.carservice.entity.*;
import com.aston.carservice.exception.BadRequestException;
import com.aston.carservice.exception.NotFoundException;
import com.aston.carservice.repository.OrderRepository;
import com.aston.carservice.repository.OrderStatusRepository;
import com.aston.carservice.service.OrderService;
import com.aston.carservice.util.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private static final Long MIN_QUANTITY = 10L;
    private static final Long AMOUNT_CONSUMABLE_TO_BUY = 100L;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponseDto getById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

    @Override
    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto create(OrderRequestDto newOrder) {
        return Optional.ofNullable(newOrder)
                .map(orderMapper::toEntity)
                .map(orderRepository::save)
                .map(orderMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public OrderResponseDto update(Long id, OrderRequestDto newOrder) {
        return orderRepository.findById(id)
                .map(entity -> orderMapper.toEntity(newOrder, entity))
                .map(orderRepository::saveAndFlush)
                .map(orderMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", id)));
    }

    @Override
    public List<OrderResponseDto> getAllOrdersCurrentCustomer(Principal principal) {
        return orderRepository.findAllByCustomerUsername(principal.getName()).stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto payOrderOfCurrentCustomer(Long orderId, Principal principal) {
        OrderEntity order = getOrderById(orderId);
        if (!order.getCustomer().getUsername().equals(principal.getName())) {
            throw new BadRequestException(String.format("Current customer doesn't have access to order with ID %s", orderId));
        }
        if (order.getOrderStatus().getName().equals("PAID")) {
            throw new BadRequestException(String.format("Order with ID %s has been already paid", orderId));
        } else if (!order.getOrderStatus().getName().equals("DONE")) {
            throw new BadRequestException(String.format("Order with ID %s has not been completed yet", orderId));
        }
        UserEntity manager = order.getManager();
        UserEntity worker = order.getWorker();
        CarServiceEntity carService = manager.getCarService();
        carService.addToBudget((long) (order.getPrice() / 2 * 1.5));
        manager.addToSalary((long) (order.getPrice() / 2 * 0.1));
        worker.addToSalary((long) (order.getPrice() / 2 * 0.4));
        order.setOrderStatus(orderStatusRepository.findByName("PAID").orElse(null));
        orderRepository.saveAndFlush(order);
        return orderMapper.toResponseDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto startOrder(Long orderId, Principal principal) {
        OrderEntity order = getOrderById(orderId);
        if (order.getWorker() == null) {
            throw new BadRequestException(String.format("Order with ID %s doesn't have a worker", orderId));
        } else if (!order.getWorker().getUsername().equals(principal.getName())) {
            throw new BadRequestException(String.format("Current worker is not assigned to an order with ID %s", orderId));
        }
        if (!order.getOrderStatus().getName().equals("ASSIGNED")) {
            throw new BadRequestException(String.format("Order with id %s is in status other than ASSIGNED", orderId));
        }
        CarServiceEntity carService = order.getWorker().getCarService();
        order.getServices().forEach(service -> {
                    service.getServiceConsumables().forEach(serviceConsumable -> {
                        ConsumableEntity consumable = serviceConsumable.getConsumable();
                        Long newQuantity = consumable.getQuantity() - serviceConsumable.getCount();
                        consumable.setQuantity(newQuantity);
                        if (newQuantity <= MIN_QUANTITY) {
                            buyConsumables(carService, consumable, AMOUNT_CONSUMABLE_TO_BUY);
                        }
                    });
                });
        order.setOrderStatus(orderStatusRepository.findByName("IN PROGRESS").orElse(null));
        orderRepository.saveAndFlush(order);
        return orderMapper.toResponseDto(order);
    }

    private void buyConsumables(CarServiceEntity carService, ConsumableEntity consumable, Long amount) {
        Long cost = consumable.getPrice()*amount;
        carService.spendBudget(cost);
        consumable.addToQuantity(amount);
    }

    private OrderEntity getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Order with ID %s not found", orderId)));
    }

}
