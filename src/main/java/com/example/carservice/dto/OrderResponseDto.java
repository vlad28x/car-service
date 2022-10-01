package com.example.carservice.dto;

import java.util.List;

public class OrderResponseDto {

    private Long id;
    private Long price;
    private OrderStatusResponseDto status;
    private List<ServiceResponseDto> services;
    private UserResponseDto user;
    private UserResponseDto manager;
    private UserResponseDto customer;

    public OrderResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public OrderStatusResponseDto getStatus() {
        return status;
    }

    public void setStatus(OrderStatusResponseDto status) {
        this.status = status;
    }

    public List<ServiceResponseDto> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponseDto> services) {
        this.services = services;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public UserResponseDto getManager() {
        return manager;
    }

    public void setManager(UserResponseDto manager) {
        this.manager = manager;
    }

    public UserResponseDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserResponseDto customer) {
        this.customer = customer;
    }

}
