package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"service", "consumable"})
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
