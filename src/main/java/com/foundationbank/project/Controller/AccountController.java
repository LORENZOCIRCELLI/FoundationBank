package com.foundationbank.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foundationbank.project.Services.AccountService;
import com.foundationbank.project.payload.DTO.AccountDTO;
import com.foundationbank.project.payload.Response.AccountResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;



@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    
    @PostMapping("/create/{userId}")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable Long userId){

        AccountDTO savedAccountDTO = accountService.createAccount(accountDTO, userId);
        return new ResponseEntity<>(savedAccountDTO, HttpStatus.CREATED);

    }

    @GetMapping("/find")
    public ResponseEntity<AccountResponse> getAllAccounts(){
        AccountResponse accountResponse = accountService.getAllAccounts();
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    @GetMapping("/find/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long accountId) {
        AccountResponse accountResponse = accountService.getAccountById(accountId);
        return new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.OK);
    }
    
    @PutMapping("/update/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable Long accountId){
        AccountDTO savedAccountDTO = accountService.updateAccount(accountDTO, accountId);
        return new ResponseEntity<>(savedAccountDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{accountId}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long accountId){
        AccountDTO deletedAccount = accountService.deleteAccount(accountId);
        return new ResponseEntity<>(deletedAccount, HttpStatus.OK);
    }

}
