package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Consumable extends AbstractEntity<Long>{

    private String name;
    private Integer price;
    private Integer quantity;

    @OneToMany(mappedBy = "consumable")
    private List<ServiceConsumable> serviceList;

}
