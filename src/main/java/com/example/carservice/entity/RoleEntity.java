package com.example.carservice.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RoleEntity extends BaseEntity<Long> {

    private String name;
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();

    public RoleEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }

}
