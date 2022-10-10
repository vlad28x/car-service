package com.aston.carservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ServiceRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;
    @Min(1)
    @NotNull
    private Long carServiceId;

    public ServiceRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
    }

}
