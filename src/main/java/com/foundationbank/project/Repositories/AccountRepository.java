package com.foundationbank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foundationbank.project.model.Account;;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
