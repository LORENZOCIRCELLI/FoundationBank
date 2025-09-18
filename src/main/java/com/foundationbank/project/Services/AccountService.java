package com.foundationbank.project.Services;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(Account account, Long userId);
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    Account updateAccount(Account account, Long id);
    void deleteAccount(Long id);

    void deposit(Long accountId, BigDecimal amount);
    void withdraw(Long accountId, BigDecimal amount);
    
}