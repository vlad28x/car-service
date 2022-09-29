package com.example.carservice.service.user;

import com.example.carservice.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUser(long id);
    public void deleteUser(long id);
}
