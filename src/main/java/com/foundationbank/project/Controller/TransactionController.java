package com.foundationbank.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foundationbank.project.Services.TransactionService;

@RestController()
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
}
