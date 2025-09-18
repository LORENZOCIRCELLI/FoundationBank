package com.foundationbank.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foundationbank.project.Repositories.UserRepository;
import com.foundationbank.project.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository; 

    @Override
    public User createUser(User user) {
        if(user == null){
            return user;
        }
        User savedUser = userRepository.save(user);
        return savedUser;        
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User updateUser(User user, Long id) {
        User userFromDb = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found or don't exist with id: " + id));
        userFromDb.setName(user.getName());
        userFromDb.setSurname(user.getSurname());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setCpf(user.getCpf());
        userFromDb.setDateOfBirth(user.getDateOfBirth());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        userFromDb.setRole(user.getRole());
        User updatedUser = userRepository.save(userFromDb);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
}