package com.example.zb_account.repository;

import com.example.zb_account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AccountUser, Long> {
}
