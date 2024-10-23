package com.wadlab.academy_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wadlab.academy_bank.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Boolean existsByEmail(String email);

}
