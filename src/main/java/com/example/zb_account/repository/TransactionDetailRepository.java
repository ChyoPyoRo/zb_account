package com.example.zb_account.repository;

import com.example.zb_account.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TransactionDetailRepository  {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> findByTransactionId(String transactionId) {
        return transactionRepository.findByTransactionIdEquals(transactionId);
    }
}
