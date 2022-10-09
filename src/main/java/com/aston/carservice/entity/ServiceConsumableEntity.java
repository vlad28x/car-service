package com.aston.carservice.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "service_consumable")
public class ServiceConsumableEntity {

    @EmbeddedId
    private ServiceConsumableId id = new ServiceConsumableId();
    @Column(name = "count", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceConsumableEntity that = (ServiceConsumableEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
