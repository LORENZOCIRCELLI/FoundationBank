package com.foundationbank.project.payload.Response;

import java.time.LocalDate;
import java.util.List;

import com.foundationbank.project.payload.DTO.UserDTO;

import lombok.Data;

@Data
public class UserResponse {
    
    /*
    * UserResponse is responsible for encapsulating the data
    * that will be returned to the client after processing.
    *
    * Responses must never expose sensitive information,
    * such as passwords or personal identifiers (civil registers).
    *
    * When designing Response DTOs, focus on providing
    * only the information that the client actually needs to see.
    *
    * Note:
    * - Response classes are strictly for output and are not persisted.
    * - They help decouple the internal data model from the API.
    */

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private LocalDate dateOfBirth;

    private List<UserDTO> userDTOsList;

}
