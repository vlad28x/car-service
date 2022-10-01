package com.example.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarServiceEntity extends BaseEntity<Long> {

    private String name;
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


    @Override
    public String toString() {
        return "CarServiceEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }

}
