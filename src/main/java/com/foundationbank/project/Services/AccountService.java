package com.foundationbank.project.Services;

import java.math.BigDecimal;

import com.foundationbank.project.payload.DTO.AccountDTO;
import com.foundationbank.project.payload.Response.AccountResponse;

public interface AccountService {

    AccountDTO createAccount(AccountDTO account, Long userId);
    AccountResponse getAccountById(Long id);
    AccountResponse getAllAccounts();
    AccountDTO updateAccount(AccountDTO account, Long id);
    AccountDTO deleteAccount(Long id);

    void deposit(Long accountId, BigDecimal amount);
    void withdraw(Long accountId, BigDecimal amount);
    
}