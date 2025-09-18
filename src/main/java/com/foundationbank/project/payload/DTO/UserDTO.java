package com.foundationbank.project.payload.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {

    /*
    * UserDTO represents the data received from the client via the API
    * and passed to the Service layer for processing.
    *
    * When designing a DTO, focus on what the client actually needs to provide
    * for the specific operation, such as creating or updating an entity.
    *
    * Note:
    * - DTOs are for input (requests), not for exposing sensitive information.
    * - Always validate the fields according to the business rules.
    */

    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Surname is required!")
    private String surname;
    @Email(message = "Valid email is required!")
    private String email;
    @NotBlank(message = "Password is required!")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
        message = "Password must have at least 8 characters, one uppercase, one lowercase, one number, and one special character"
    )
    private String password;
    @NotNull(message = "Birth Date is required!")
    private LocalDate dateOfBirth;
    @NotBlank(message = "CPF is required!")
    @Pattern(
    regexp = "^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
    message = "CPF must be either 11 digits or in the format XXX.XXX.XXX-XX"
    )
    private String cpf;
    @NotBlank(message = "Phone Number is required!")
    private String phoneNumber;

}
