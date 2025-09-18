package com.foundationbank.project.model;

import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foundationbank.project.model.base.Auditable;
import com.foundationbank.project.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends Auditable{

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
    @Column(nullable = false)
    private Role role;
    @NotBlank
    @Size(min = 11, max = 11, message = "The CPF must be exactly 11 digits!")
    private String cpf;
    @NotBlank
    private String phoneNumber;
    @NotNull(message = "Date of Birth is required!")
    private LocalDate dateOfBirth;
    
    public User(String name, String surname, String email, String password, String cpf, String phoneNumber, LocalDate dateOfBirth){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    @OneToMany(mappedBy = "user")
    private List<Account> userAccounts = new ArrayList<>();

}