package com.example.carservice.dto;

public class OrderStatusResponseDto {

    private Long id;
    private String name;

    public OrderStatusResponseDto() {
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

}
