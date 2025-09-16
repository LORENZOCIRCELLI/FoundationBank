package com.foundationbank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foundationbank.project.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
