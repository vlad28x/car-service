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
public class Consumable extends AbstractEntity<Long>{

    private String name;
    private Long price;
    private Long quantity;

    @OneToMany(mappedBy = "consumable")
    private List<ServiceConsumable> serviceList = new ArrayList<>();

}
