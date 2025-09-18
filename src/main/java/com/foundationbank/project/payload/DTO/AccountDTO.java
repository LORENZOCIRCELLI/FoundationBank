package com.foundationbank.project.payload.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {
    
    /*
    * AccountDTO represents the data received from the client via the API
    * and passed to the Service layer for processing.
    *
    * When designing a DTO, focus on what the client actually needs to provide
    * for the specific operation, such as creating or updating an entity.
    *
    * Note:
    * - DTOs are for input (requests), not for exposing sensitive information.
    * - Always validate the fields according to the business rules.
    */
    @NotNull
    private Long accountId;
    @NotBlank
    private String accountType;
    @NotNull
    private BigDecimal initialBalance;


}
