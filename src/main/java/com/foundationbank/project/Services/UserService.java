package com.foundationbank.project.Services;

import java.util.List;
import java.util.Optional;

import com.foundationbank.project.model.User;
import com.foundationbank.project.payload.DTO.UserDTO;
import com.foundationbank.project.payload.Response.UserResponse;

public interface UserService{

    UserDTO createUser(User user);
    UserResponse getUserById(Long id);
    UserResponse getAllUsers();
    UserDTO updateUser(User user, Long id);
    UserDTO deleteUser(Long id);
    
}