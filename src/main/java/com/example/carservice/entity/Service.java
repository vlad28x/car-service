package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Service extends AbstractEntity<Long> {

    private String name;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "car_service_id", referencedColumnName = "id")
    private CarService carService;

    @OneToMany(mappedBy = "service")
    private List<ServiceConsumable> consumableList;

    @ManyToMany(mappedBy = "services")
    private List<Orders> orders;

}
