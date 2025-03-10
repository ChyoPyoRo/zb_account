package com.example.zb_account.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCancelResponseDTO {
    private String accountNumber;
    private String transactionResult;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactedAt;
}
