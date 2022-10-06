package com.aston.carservice.dto;

import java.util.Objects;

public class ServiceResponseDto {

    private Long id;
    private String name;
    private Long price;
    private Long carServiceId;

    public ServiceResponseDto() {
    }

    public ServiceResponseDto(Long id) {
        this.id = id;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceResponseDto that = (ServiceResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(carServiceId, that.carServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, carServiceId);
    }

}
