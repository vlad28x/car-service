package com.example.carservice.dto;

public class CarServiceRequestDto {

    private String name;
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
