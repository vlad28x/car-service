package com.example.carservice.dto;

public class ServiceConsumableResponseDto {

    private ServiceResponseDto service;
    private ConsumableResponseDto consumable;
    private Long count;

    public ServiceConsumableResponseDto() {
    }

    public ServiceResponseDto getService() {
        return service;
    }

    public void setService(ServiceResponseDto service) {
        this.service = service;
    }

    public ConsumableResponseDto getConsumable() {
        return consumable;
    }

    public void setConsumable(ConsumableResponseDto consumable) {
        this.consumable = consumable;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
