package com.aston.carservice.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceConsumableResponseDto that = (ServiceConsumableResponseDto) o;
        return Objects.equals(service, that.service) && Objects.equals(consumable, that.consumable) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, consumable, count);
    }

}
