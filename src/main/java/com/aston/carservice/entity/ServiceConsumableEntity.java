package com.aston.carservice.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
