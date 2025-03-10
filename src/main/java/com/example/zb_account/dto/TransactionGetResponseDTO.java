package com.example.zb_account.dto;

import com.example.zb_account.aop.AccountLockIdInterface;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionGetResponseDTO {
    private String accountNumber;
    private String transactionType;
    private String transactionResult;
    private String transactionId;
    private Long amount;
    private LocalDateTime transactedAt;

}
