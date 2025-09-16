package com.foundationbank.project.model;

import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @NotBlank
    @Size(min = 2, message = "Name must be atleast 2 characters long!")
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Email(message = "Please insert a valid email!")
    private String email;
    @NotBlank
    @Size(min = 8, message = "Password must be atleast 8 characters long!")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @NotBlank
    @Size(min = 11, max = 11, message = "The CPF must be exactly 11 digits!")
    private String CPF;
    @NotBlank
    private String phoneNumber;
    @NotNull(message = "Date of Birth is required!")
    private LocalDate dateOfBirth;
    
    public User(String name, String surname, String email, String password, String CPF, String phoneNumber, LocalDate dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.CPF = CPF;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    /*For audict purposes, this variables will store the time the user was registered and
    the last time it was updated*/
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
