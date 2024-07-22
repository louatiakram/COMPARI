package com.FindMyPc.back.service;

import com.FindMyPc.back.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(int id);

    User saveUser(User user);
    
    User updateUser(User user);

    void deleteUser(int id);
}
