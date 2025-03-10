package com.example.zb_account.repository;


import com.example.zb_account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankingRepository {
    @Autowired
    private AccountRepository accountRepository;

    public void takeMoneyInAccount(Account account) {

    }

}
