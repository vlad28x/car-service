package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class ServiceConsumableId implements Serializable {

    private Long serviceId;
    private Long consumableId;

}
