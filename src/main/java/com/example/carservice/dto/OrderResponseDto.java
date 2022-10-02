package com.example.carservice.dto;

import java.util.List;

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

    public void setUser(UserResponseDto worker) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        if (services != null ? !services.equals(that.services) : that.services != null)
            return false;
        if (worker != null ? !worker.equals(that.worker) : that.worker != null)
            return false;
        if (manager != null ? !manager.equals(that.manager) : that.manager != null)
            return false;
        return customer != null ? customer.equals(that.customer) : that.customer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (services != null ? services.hashCode() : 0);
        result = 31 * result + (worker != null ? worker.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }
}
