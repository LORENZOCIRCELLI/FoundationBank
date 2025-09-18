package com.foundationbank.project.Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundationbank.project.exceptions.ResourceNotFoundException;
import com.foundationbank.project.exceptions.APIException;
import com.foundationbank.project.Repositories.AccountRepository;
import com.foundationbank.project.Repositories.TransactionRepository;
import com.foundationbank.project.Repositories.UserRepository;
import com.foundationbank.project.model.Account;
import com.foundationbank.project.model.Transaction;
import com.foundationbank.project.model.User;
import com.foundationbank.project.model.enums.AccountStatus;
import com.foundationbank.project.model.enums.AccountType;
import com.foundationbank.project.model.enums.TransactionType;
import com.foundationbank.project.payload.DTO.AccountDTO;
import com.foundationbank.project.payload.Response.AccountResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    protected TransactionServiceImplementation transactionServiceImplementation;

    protected Integer agencyNumberCounter = 3000;

    @Override
    public AccountDTO createAccount(AccountDTO account, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        
        List<Account> accountList = user.getUserAccounts();

        boolean accountExists = accountList.stream().anyMatch(a -> a.getAccountType().equals(account.getAccountType()));

        if(accountExists){
            Account createdAccount = modelMapper.map(account, Account.class);

            if(account.getAccountType().equals(AccountType.CHECKING)){
                createdAccount.setAccountType(AccountType.CHECKING);
            }else if(account.getAccountType().equals(AccountType.SAVINGS)){
                createdAccount.setAccountType(AccountType.SAVINGS);
            }

            createdAccount.setAgencyNumber("001");
            createdAccount.setAccountNumber(Integer.toString(agencyNumberCounter++));
            createdAccount.setBalance(BigDecimal.ZERO);
            createdAccount.setAccountStatus(AccountStatus.ACTIVE);

            createdAccount.setUser(user);

            Account savedAccount = accountRepository.save(createdAccount);
            return modelMapper.map(savedAccount, AccountDTO.class);

        }else{
            throw new APIException("Account already exists!");
        }

        

    }

    @Override
    public AccountResponse getAccountById(Long id) {

        Account dbAccount = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("The id provided does not exists in the database"));
        
        return modelMapper.map(dbAccount, AccountResponse.class);

    }

    @Override
    public AccountResponse getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOs = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).toList();

        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setContent(accountDTOs);
        
        return accountResponse;

    }

    @Override
    public AccountDTO updateAccount(AccountDTO account, Long id) {

        Account accountFromDb = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("account","accountId",id));

        Account mappedAccount = modelMapper.map(account, Account.class);

        accountFromDb.setAccountType(mappedAccount.getAccountType());
        accountFromDb.setAccountNumber(mappedAccount.getAccountNumber());

        Account savedAccount = accountRepository.save(accountFromDb);
        return modelMapper.map(savedAccount, AccountDTO.class);

    }

    @Override
    public AccountDTO deleteAccount(Long id) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", id));
        accountRepository.delete(account);
        return modelMapper.map(account, AccountDTO.class);
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