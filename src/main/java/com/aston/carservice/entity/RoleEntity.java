package com.aston.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();

    public RoleEntity() {
    }

    public RoleEntity(Long id) {
        super(id);
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
