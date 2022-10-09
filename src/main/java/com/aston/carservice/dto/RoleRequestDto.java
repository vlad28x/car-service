package com.aston.carservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleRequestDto {

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    public RoleRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
