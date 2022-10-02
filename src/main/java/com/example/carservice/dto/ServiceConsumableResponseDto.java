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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceConsumableResponseDto that = (ServiceConsumableResponseDto) o;

        if (service != null ? !service.equals(that.service) : that.service != null)
            return false;
        if (consumable != null ? !consumable.equals(that.consumable) : that.consumable != null)
            return false;
        return count != null ? count.equals(that.count) : that.count == null;
    }

    @Override
    public int hashCode() {
        int result = service != null ? service.hashCode() : 0;
        result = 31 * result + (consumable != null ? consumable.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}
