package com.example.carservice.dto;

public class OrderStatusRequestDto {

    private Long id;
    private String name;

    public OrderStatusRequestDto() {
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
