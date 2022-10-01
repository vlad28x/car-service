package com.example.carservice.dto;

public class OrderStatusRequestDto {

    private Long id;
    private String status;

    public OrderStatusRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
