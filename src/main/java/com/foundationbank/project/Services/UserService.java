package com.foundationbank.project.Services;

import java.util.List;

import com.foundationbank.project.payload.DTO.UserDTO;
import com.foundationbank.project.payload.Response.UserResponse;

public interface UserService{

    UserDTO createUser(UserDTO user);
    UserResponse getUserById(Long userId);
    List<UserResponse> getAllUsers();
    UserDTO updateUser(UserDTO user, Long userId);
    UserDTO deleteUser(Long userId);
    
}