package com.example.carservice.service.role;

import com.example.carservice.entity.Role;
import com.example.carservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRole(long id) {
        Role role = new Role();
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;
    }

    @Override
    public void deleteUser(long id) {
        roleRepository.deleteById(id);
    }
}
