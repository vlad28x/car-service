package com.aston.carservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConsumableRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;
    @NotNull
    @Min(0)
    private Long price;
    @NotNull
    @Min(0)
    private Long quantity;

    public ConsumableRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
