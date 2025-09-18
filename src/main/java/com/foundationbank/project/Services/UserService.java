package com.foundationbank.project.Services;

import java.util.List;
import java.util.Optional;

import com.foundationbank.project.model.User;

public interface UserService{

    User createUser(User user);
    Optional<User> getUserById(Long id);
    List<User>getAllUsers();
    User updateUser(User user, Long id);
    void deleteUser(Long id);
    
}