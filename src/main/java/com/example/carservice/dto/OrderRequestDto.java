package com.example.carservice.dto;

import java.util.List;

public class OrderRequestDto {

    private Long price;
    private Long statusId;
    private List<Long> servicesId;
    private Long workerId;
    private Long managerId;
    private Long customerId;

    public OrderRequestDto() {
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public List<Long> getServicesId() {
        return servicesId;
    }

    public void setServicesId(List<Long> servicesId) {
        this.servicesId = servicesId;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
