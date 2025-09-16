package com.foundationbank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foundationbank.project.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
