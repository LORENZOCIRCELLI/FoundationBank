package com.foundationbank.project.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundationbank.project.Repositories.AccountRepository;
import com.foundationbank.project.Repositories.TransactionRepository;
import com.foundationbank.project.Repositories.UserRepository;
import com.foundationbank.project.model.Account;
import com.foundationbank.project.model.Transaction;
import com.foundationbank.project.model.User;
import com.foundationbank.project.model.enums.TransactionType;
import com.foundationbank.project.payload.DTO.AccountDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final TransactionRepository transactionRepository;
    @Autowired
    protected TransactionServiceImplementation transactionServiceImplementation;

    @Override
    public Account createAccount(Account account, Long userId) {

        Optional<Account> dbAccount = accountRepository.findById(account.getAccountId());

        if(dbAccount.isPresent()){
            throw new RuntimeException("The account already exists");
        }
        User userAccount = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("The user id provided does not exist"));
        if(userAccount.getUserAccounts().contains(account)){
            throw new RuntimeException("The account already exists for the user");
        }
        account.setUser(userAccount);
        return accountRepository.save(account);

    }

    @Override
    public Account getAccountById(Long id) {

        Account dbAccount = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("The id provided does not exists in the database"));
        return dbAccount;

    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> allAccounts = accountRepository.findAll();

        return allAccounts;

    }

    @Override
    public Account updateAccount(Account account, Long id) {

        Account dbAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("The account does not exists in the repository"));

        dbAccount.setAccountNumber(account.getAccountNumber());
        dbAccount.setAgencyNumber(account.getAgencyNumber());
        dbAccount.setAccountType(account.getAccountType());
        dbAccount.setAccountStatus(account.getAccountStatus());

        Account savedAccount = accountRepository.save(dbAccount);
        return savedAccount;

    }

    @Override
    public void deleteAccount(Long id) {

        Optional<Account> vdAccount = accountRepository.findById(id);
        if(vdAccount.isPresent()){

            Account dbAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("The account does not exist"));
            accountRepository.delete(dbAccount);
        }

    }

    @Override
    public void deposit(Long accountId, BigDecimal amount) {

        Account dbAccount = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account id"));

        BigDecimal actualBalance = dbAccount.getBalance();
        BigDecimal updatedBalance = actualBalance.add(amount);
        dbAccount.setBalance(updatedBalance);
        accountRepository.save(dbAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(dbAccount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transactionRepository.save(transaction);

    }

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {

        Account dbAccount = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account id"));

        BigDecimal actualBalance = dbAccount.getBalance();
        BigDecimal updatedBalance = actualBalance.subtract(amount);
        dbAccount.setBalance(updatedBalance);
        accountRepository.save(dbAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(dbAccount);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transactionRepository.save(transaction);

    }
    
}