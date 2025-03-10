package com.example.zb_account.repository;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<List<Account>> findAllByAccountUser(AccountUser accountUser);

    Optional<Account> findByAccountNumber(String accountNumber);

    int countByAccountUser(AccountUser accountUser);

    @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber " +
            "AND a.accountUser = :accountUser "+
            "AND a.accountStatus = 'IN_USE' ")
    Optional<Account> findValidAccount(@Param("accountNumber") String accountNumber,
                                       @Param("accountUser") AccountUser accountUser);
}
