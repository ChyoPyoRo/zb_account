package com.example.zb_account.repository;

import com.example.zb_account.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Optional<Transaction> findByTransactionIdEquals(String transactionId);
}
