package com.foundationbank.project.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foundationbank.project.Repositories.AccountRepository;
import com.foundationbank.project.Repositories.TransactionRepository;
import com.foundationbank.project.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class TransactionServiceImplementation implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Transaction> getAllTransactionsFromAccount(Long accountId) {

        List<Transaction> transactions = transactionRepository.findByAccountAccountId(accountId);
        return transactions;

    }

    @Override
    public Transaction createTransaction(Transaction transaction, Long accountId){

        List<Transaction> transactions = transactionRepository.findByAccountAccountId(accountId);
        if(transactions.contains(transaction)){
            throw new RuntimeException("This transaction already exists");
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        return savedTransaction;

    }

    @Override
    public Transaction getTransactionById(Long id) {

        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("This transaction don't exist in the database"));
        return transaction;
        

    }
    
}