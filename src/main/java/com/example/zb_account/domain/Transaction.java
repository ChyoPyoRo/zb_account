package com.example.zb_account.domain;

import com.example.zb_account.type.TransactionType;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @Column
    private TransactionType transactionType;
}
