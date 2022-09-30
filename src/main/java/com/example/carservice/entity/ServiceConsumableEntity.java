package com.example.carservice.entity;

import javax.persistence.*;


@Entity
public class ServiceConsumableEntity {

    @EmbeddedId
    private ServiceConsumableId id;
    private Long count;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("serviceId")
    private ServiceEntity service;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("consumableId")
    private ConsumableEntity consumable;

    public ServiceConsumableEntity() {
    }

    public ServiceConsumableId getId() {
        return id;
    }

    public void setId(ServiceConsumableId id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public ConsumableEntity getConsumable() {
        return consumable;
    }

    public void setConsumable(ConsumableEntity consumable) {
        this.consumable = consumable;
    }

    @Override
    public String toString() {
        return "ServiceConsumableEntity{" +
                "serviceId=" + id.getServiceId() +
                ", consumableId=" + id.getConsumableId() +
                ", count=" + count +
                '}';
    }

}
