package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class CarService extends AbstractEntity<Long> {

    private Long budget;
    private String name;

    @OneToMany(mappedBy = "carService")
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "carService")
    private List<Service> services = new ArrayList<>();
}
