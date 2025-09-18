package com.foundationbank.project.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundationbank.project.Repositories.AccountRepository;
import com.foundationbank.project.Repositories.TransactionRepository;
import com.foundationbank.project.exceptions.ResourceNotFoundException;
import com.foundationbank.project.model.Account;
import com.foundationbank.project.model.Transaction;
import com.foundationbank.project.payload.DTO.TransactionDTO;
import com.foundationbank.project.payload.Response.TransactionResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class TransactionServiceImplementation implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public TransactionResponse getAllTransactionsFromAccount(Long accountId) {

        Account searchedAccount = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("account", "accountId", accountId));
        
        List<TransactionDTO> allTransactionDTOsListFromAccount = searchedAccount
        .getAccountTransactions()
        .stream()
        .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
        .toList();

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionsList(allTransactionDTOsListFromAccount);
        return transactionResponse;


    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transaction, Long accountId){

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("account", "accountId", accountId));
        
        Transaction createdTransaction = modelMapper.map(transaction, Transaction.class);

        createdTransaction.setTransactionDate(LocalDateTime.now());
        createdTransaction.setTransactionType(transaction.getTransactionType());

        createdTransaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(createdTransaction); 

        return modelMapper.map(savedTransaction, TransactionDTO.class);

    }

    @Override
    public TransactionResponse getTransactionById(Long id) {

        Transaction transaction = transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("This transaction don't exist in the database"));
        return modelMapper.map(transaction, TransactionResponse.class);
        

    }
    
}