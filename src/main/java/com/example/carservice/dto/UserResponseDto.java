package com.example.carservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long salary;
    private RoleResponseDto role;
    private Long carServiceId;

    public UserResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public RoleResponseDto getRole() {
        return role;
    }

    public void setRole(RoleResponseDto role) {
        this.role = role;
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
        UserResponseDto that = (UserResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(salary, that.salary) && Objects.equals(role, that.role) && Objects.equals(carServiceId, that.carServiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, salary, role, carServiceId);
    }

}
