package com.example.carservice.service.role;

import com.example.carservice.entity.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();
    public void saveRole(Role user);
    public Role getRole(long id);
    public void deleteUser(long id);
}
