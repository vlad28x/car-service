package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "serviceConsumables", callSuper = true)
@EqualsAndHashCode(exclude = "serviceConsumables", callSuper = true)
public class Consumable extends AbstractEntity<Long> {

    private String name;
    private Long price;
    private Long quantity;
    @OneToMany(mappedBy = "consumable")
    private List<ServiceConsumable> serviceConsumables = new ArrayList<>();

}
