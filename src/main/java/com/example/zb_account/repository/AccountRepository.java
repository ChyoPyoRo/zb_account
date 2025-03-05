package com.example.zb_account.repository;

import com.example.zb_account.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
