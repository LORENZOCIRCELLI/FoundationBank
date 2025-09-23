package com.foundationbank.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foundationbank.project.Services.UserService;
import com.foundationbank.project.payload.DTO.UserDTO;
import com.foundationbank.project.payload.Response.UserResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }


    @GetMapping("/find")
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {

        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId){
        UserDTO deletedUser = userService.deleteUser(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
    

    
    
}
