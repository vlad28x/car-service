package com.aston.carservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDto {

    @NotNull
    @Size(min = 3, max = 255)
    private String username;
    @NotNull
    @Size(min = 6, max = 255)
    private String password;
    @Email
    @NotNull
    private String email;
    @Min(0)
    private Long salary;
    @Min(1)
    @NotNull
    private Long roleId;
    @Min(1)
    @NotNull
    private Long carServiceId;

    public UserRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCarServiceId() {
        return carServiceId;
    }

    public void setCarServiceId(Long carServiceId) {
        this.carServiceId = carServiceId;
    }

}
