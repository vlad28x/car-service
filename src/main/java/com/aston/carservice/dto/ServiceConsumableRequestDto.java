package com.aston.carservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ServiceConsumableRequestDto {

    @NotNull
    @Min(1)
    private Long serviceId;
    @NotNull
    @Min(1)
    private Long consumableId;
    @NotNull
    @Min(0)
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
