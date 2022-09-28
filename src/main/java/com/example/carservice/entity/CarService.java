package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class CarService extends AbstractEntity<Long> {

    private Integer budget;
    private String name;

    @OneToMany(mappedBy = "carService")
    private List<Users> users;
    @OneToMany(mappedBy = "carService")
    private List<Service> services;
}
