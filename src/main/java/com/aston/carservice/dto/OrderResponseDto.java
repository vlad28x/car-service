package com.aston.carservice.dto;

import java.util.List;
import java.util.Objects;

public class OrderResponseDto {

    private Long id;
    private Long price;
    private OrderStatusResponseDto status;
    private List<ServiceResponseDto> services;
    private UserResponseDto worker;
    private UserResponseDto manager;
    private UserResponseDto customer;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id) {
        this.id = id;
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

    public UserResponseDto getWorker() {
        return worker;
    }

    public void setWorker(UserResponseDto worker) {
        this.worker = worker;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderResponseDto that = (OrderResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(status, that.status) && Objects.equals(services, that.services) && Objects.equals(worker, that.worker) && Objects.equals(manager, that.manager) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, status, services, worker, manager, customer);
    }

}
