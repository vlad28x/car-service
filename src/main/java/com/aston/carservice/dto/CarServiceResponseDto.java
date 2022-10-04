package com.aston.carservice.dto;

import java.util.Objects;

public class CarServiceResponseDto {

    private Long id;
    private String name;
    private Long budget;

    public CarServiceResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarServiceResponseDto that = (CarServiceResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(budget, that.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, budget);
    }

}
