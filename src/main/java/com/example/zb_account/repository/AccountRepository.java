package com.example.zb_account.repository;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);

    int countByAccountUser(AccountUser accountUser);
}
