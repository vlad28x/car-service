package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ServiceConsumable {

    @EmbeddedId
    private ServiceConsumableId id;

    private Long count;

    @ManyToOne
    @MapsId("serviceId")
    private Service service;

    @ManyToOne
    @MapsId("consumableId")
    private Consumable consumable;

}
