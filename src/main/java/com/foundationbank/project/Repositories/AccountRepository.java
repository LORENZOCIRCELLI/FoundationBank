package com.foundationbank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foundationbank.project.model.Account;;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    
}