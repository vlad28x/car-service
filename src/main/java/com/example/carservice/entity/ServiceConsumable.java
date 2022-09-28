package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class ServiceConsumable {

    @EmbeddedId
    private ServiceConsumableId id;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "consumable_id", referencedColumnName = "id")
    private Consumable consumable;

    /*service_id    bigint references service (id),
    consumable_id bigint references consumable (id),
    count         bigint not null,
    primary key (service_id, consumable_id)*/
}
