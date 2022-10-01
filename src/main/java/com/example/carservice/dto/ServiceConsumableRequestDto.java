package com.example.carservice.dto;

public class ServiceConsumableRequestDto {

    private Long serviceId;
    private Long consumableId;
    private Long count;

    public ServiceConsumableRequestDto() {
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getConsumableId() {
        return consumableId;
    }

    public void setConsumableId(Long consumableId) {
        this.consumableId = consumableId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
