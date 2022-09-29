package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Service extends AbstractEntity<Long> {

    private String name;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "car_service_id", referencedColumnName = "id")
    private CarService carService;

    @OneToMany(mappedBy = "service")
    private List<ServiceConsumable> consumableList = new ArrayList<>();

    @ManyToMany(mappedBy = "services")
    private List<Order> orders = new ArrayList<>();

}
