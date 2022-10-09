package com.aston.carservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceConsumableId implements Serializable {

    @Column(name = "service_id", nullable = false)
    private Long serviceId;
    @Column(name = "consumable_id", nullable = false)
    private Long consumableId;

    public ServiceConsumableId() {
    }

    public ServiceConsumableId(Long serviceId, Long consumableId) {
        this.serviceId = serviceId;
        this.consumableId = consumableId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getConsumableId() {
        return consumableId;
    }

    public void setConsumableId(Long consumableId) {
        this.consumableId = consumableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceConsumableId that = (ServiceConsumableId) o;
        return Objects.equals(serviceId, that.serviceId) && Objects.equals(consumableId, that.consumableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, consumableId);
    }

    @Override
    public String toString() {
        return "ServiceConsumableId{" +
                "serviceId=" + serviceId +
                ", consumableId=" + consumableId +
                '}';
    }

}
