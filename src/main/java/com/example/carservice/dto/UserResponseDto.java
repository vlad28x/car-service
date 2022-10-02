package com.example.carservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null)
            return false;
        if (salary != null ? !salary.equals(that.salary) : that.salary != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null)
            return false;
        return carServiceId != null ? carServiceId.equals(that.carServiceId) : that.carServiceId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (carServiceId != null ? carServiceId.hashCode() : 0);
        return result;
    }
}
