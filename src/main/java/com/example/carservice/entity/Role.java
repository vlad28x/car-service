package com.example.carservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Role extends AbstractEntity<Long> {

    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

}
