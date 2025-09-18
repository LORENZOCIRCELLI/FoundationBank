package com.foundationbank.project.payload.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.foundationbank.project.model.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionDTO {

    private Long transactionId;

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Transaction date is required")
    private LocalDateTime transactionDate;

    @NotNull(message = "Account ID is required")
    private Long accountId;

}
