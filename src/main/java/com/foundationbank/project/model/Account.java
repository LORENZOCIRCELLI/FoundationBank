package com.foundationbank.project.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.foundationbank.project.model.base.Auditable;
import com.foundationbank.project.model.enums.AccountStatus;
import com.foundationbank.project.model.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accounts")
@EqualsAndHashCode(callSuper = false)
public class Account extends Auditable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String agencyNumber;

    @Column(precision = 19, scale =2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    public Account(String accountNumber, String agencyNumber, BigDecimal balance, AccountType accountType, AccountStatus accountStatus){
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.accountStatus = accountStatus; 
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "account", nullable = false)
    private List<Transaction> accountTransactions = new ArrayList<>();

}