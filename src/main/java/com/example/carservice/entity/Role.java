package com.example.carservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "users", callSuper = true)
@EqualsAndHashCode(exclude = "users", callSuper = true)
public class Role extends AbstractEntity<Long> {

    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

}
