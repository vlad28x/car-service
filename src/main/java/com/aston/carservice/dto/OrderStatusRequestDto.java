package com.aston.carservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderStatusRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    public OrderStatusRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
