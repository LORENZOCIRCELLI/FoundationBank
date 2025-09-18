package com.foundationbank.project.Services;

import java.util.List;

import com.foundationbank.project.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction, Long accountId);
    Transaction getTransactionById(Long id);
    List<Transaction> getAllTransactionsFromAccount(Long accountId);
    
}