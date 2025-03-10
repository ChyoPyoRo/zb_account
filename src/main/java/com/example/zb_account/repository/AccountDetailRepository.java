package com.example.zb_account.repository;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import com.example.zb_account.type.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public class AccountDetailRepository {
    @Autowired
    private AccountRepository accountRepository;

    public Optional<List<Account>> findAllByAccountName(AccountUser accountUser) {
        return accountRepository.findAllByAccountUser(accountUser);
    }

    public void accountStatusUnRegistered(Account account){

        account.setAccountStatus(AccountStatus.UNREGISTERED);
    }

    public Optional<Account> findValidAccount(String accountNumber, AccountUser accountUser) {
        return accountRepository.findValidAccount(accountNumber, accountUser);
    }

    public Account createAccount(AccountUser accountUser, String accountNumber, Long money) {
        Account newAccount = Account.builder()
                .accountUser(accountUser)
                .accountNumber(accountNumber)
                .balance(money)
                .registeredAt(LocalDateTime.now())
                .unRegisteredAt(null)
                .accountStatus(AccountStatus.IN_USE)
                .build();
        return accountRepository.save(newAccount);
    }

    public boolean accountMoreThenTen(AccountUser accountUser){
        if(accountRepository.countByAccountUser(accountUser) >= 10){
            return true;
        }
        return false;
    }

    public boolean getAccount(String  accountNumber) {
        if(accountRepository.findByAccountNumber(accountNumber).isEmpty()) {
            return true;
        }
        return false;
    }
}
