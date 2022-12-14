package com.aston.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car_service")
public class CarServiceEntity extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "budget", nullable = false)
    private Long budget;
    @OneToMany(mappedBy = "carService")
    private List<UserEntity> users = new ArrayList<>();
    @OneToMany(mappedBy = "carService")
    private List<ServiceEntity> services = new ArrayList<>();

    public CarServiceEntity() {
    }

    public CarServiceEntity(Long id) {
        super(id);
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public void addToBudget(Long difference) {
        budget += difference;
    }

    public void spendBudget(Long cost) {
        budget -= cost;
    }

    @Override
    public String toString() {
        return "CarServiceEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }

}
