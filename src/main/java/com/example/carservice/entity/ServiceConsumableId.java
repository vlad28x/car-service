package com.example.carservice.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ServiceConsumableId implements Serializable {

    private Long serviceId;
    private Long consumableId;

    public ServiceConsumableId() {
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
