package com.foundationbank.project.Services;

import com.foundationbank.project.payload.DTO.TransactionDTO;
import com.foundationbank.project.payload.Response.TransactionResponse;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionDTO transaction, Long accountId);
    TransactionResponse getTransactionById(Long id);
    TransactionResponse getAllTransactionsFromAccount(Long accountId);
    
}