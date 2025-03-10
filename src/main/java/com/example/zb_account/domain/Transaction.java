package com.example.zb_account.domain;

import com.example.zb_account.dto.TransactionCancelResponseDTO;
import com.example.zb_account.dto.TransactionGetResponseDTO;
import com.example.zb_account.dto.TransactionuseResponseDTO;
import com.example.zb_account.type.TransactionResultType;
import com.example.zb_account.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionResultType transactionResultType;

    @Column
    private Long amount;

    @Column
    private Long balanceSnapshot;

    @Column
    private String transactionId;

    @Column
    private LocalDateTime transactedAt;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    public TransactionuseResponseDTO toUseResponseDTO() {
        return TransactionuseResponseDTO.builder()
                .accountNumber(this.account.getAccountNumber())
                .transactionResult(this.transactionResultType.toString())
                .transactionId(this.transactionId)
                .amount(this.amount)
                .transactedAt(LocalDateTime.now())
                .build();
    }
    public TransactionCancelResponseDTO toCancelResponseDTO() {
        return TransactionCancelResponseDTO.builder()
                .accountNumber(this.account.getAccountNumber())
                .transactionResult(this.transactionResultType.toString())
                .transactionId(this.transactionId)
                .amount(this.amount)
                .transactedAt(LocalDateTime.now())
                .build();
    }

    public TransactionGetResponseDTO toGetResponseDTO() {
        return TransactionGetResponseDTO.builder()
                .accountNumber(this.account.getAccountNumber())
                .transactionResult(this.transactionResultType.toString())
                .transactionType(this.transactionType.toString())
                .transactionId(this.transactionId)
                .amount(this.amount)
                .transactedAt(this.createdAt)
                .build();
    }
}
