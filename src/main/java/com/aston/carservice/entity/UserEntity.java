package com.aston.carservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity<Long> {

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "salary")
    private Long salary;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
    @ManyToOne
    @JoinColumn(name = "car_service_id", nullable = false)
    private CarServiceEntity carService;
    @OneToMany(mappedBy = "worker")
    private List<OrderEntity> workerOrders = new ArrayList<>();
    @OneToMany(mappedBy = "manager")
    private List<OrderEntity> managerOrders = new ArrayList<>();
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> customerOrders = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id) {
        super(id);
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public CarServiceEntity getCarService() {
        return carService;
    }

    public void setCarService(CarServiceEntity carService) {
        this.carService = carService;
    }

    public List<OrderEntity> getWorkerOrders() {
        return workerOrders;
    }

    public void setWorkerOrders(List<OrderEntity> workerOrders) {
        this.workerOrders = workerOrders;
    }

    public List<OrderEntity> getManagerOrders() {
        return managerOrders;
    }

    public void setManagerOrders(List<OrderEntity> managerOrders) {
        this.managerOrders = managerOrders;
    }

    public List<OrderEntity> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<OrderEntity> customerOrders) {
        this.customerOrders = customerOrders;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

}
