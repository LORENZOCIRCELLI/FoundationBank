package com.foundationbank.project.Services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundationbank.project.Repositories.UserRepository;
import com.foundationbank.project.exceptions.APIException;
import com.foundationbank.project.exceptions.ResourceNotFoundException;
import com.foundationbank.project.model.User;
import com.foundationbank.project.model.enums.Role;
import com.foundationbank.project.payload.DTO.UserDTO;
import com.foundationbank.project.payload.Response.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
    
        List<User> usersList = userRepository.findAll();

        String cpfDTO = userDTO.getCpf().replace(".", "");
        cpfDTO.replace("-", "");

        boolean userExists = usersList.stream().anyMatch(a -> a.getCpf().equals(cpfDTO));

        if(!userExists){
            User createdUser = modelMapper.map(userDTO, User.class);
            createdUser.setCpf(cpfDTO);
            createdUser.setRole(Role.RETAIL);

            User savedUser = userRepository.save(createdUser);
            return modelMapper.map(savedUser, UserDTO.class);

        }else{
            
            throw new APIException("The user already exists in the bank!");

        }
    
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User userFromDb = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        return modelMapper.map(userFromDb, UserResponse.class);

    }

    @Override
    public UserResponse getAllUsers() {

        List<User> allUsersList = userRepository.findAll();
        List<UserDTO> userDTOsList = allUsersList.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        UserResponse userResponse = new UserResponse();
        userResponse.setUserDTOsList(userDTOsList);
        return userResponse;

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {

        User userFromDb = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found or don't exist with id: " + id));
        
        User mappedUser = modelMapper.map(userDTO, User.class); 
        
        userFromDb.setName(mappedUser.getName());
        userFromDb.setSurname(mappedUser.getSurname());
        userFromDb.setEmail(mappedUser.getEmail());
        userFromDb.setPassword(mappedUser.getPassword());
        userFromDb.setCpf(mappedUser.getCpf());
        userFromDb.setDateOfBirth(mappedUser.getDateOfBirth());
        userFromDb.setPhoneNumber(mappedUser.getPhoneNumber());

        User savedUser = userRepository.save(userFromDb);
        return modelMapper.map(savedUser, UserDTO.class);
    
    }

    @Override
    public UserDTO deleteUser(Long userId) {

        User userFromDb = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        userRepository.delete(userFromDb);
        return modelMapper.map(userFromDb, UserDTO.class);

    }
    
}