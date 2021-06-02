package com.cachetest.bankapplication.repository;

import com.cachetest.bankapplication.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByAccountNumber(String accountNumber);

    UserAccount deleteByAccountNumber(String accountNumber);
}