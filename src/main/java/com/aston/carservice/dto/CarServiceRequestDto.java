package com.aston.carservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarServiceRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String name;
    @NotNull
    @Min(0)
    private Long budget;

    public CarServiceRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

}
