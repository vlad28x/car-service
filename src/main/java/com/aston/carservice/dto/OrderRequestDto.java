package com.aston.carservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderRequestDto {

    @Min(1)
    @NotNull
    private Long statusId;
    @Size(min = 1, max = 10)
    @NotNull
    private List<Long> servicesId;
    @Min(1)
    private Long workerId;
    @Min(1)
    private Long managerId;
    @Min(1)
    @NotNull
    private Long customerId;

    public OrderRequestDto() {
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
