package com.example.zb_account.dto;

import com.example.zb_account.aop.AccountLockIdInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCancelRequestDTO implements AccountLockIdInterface {
    private String transactionId;
    private String accountNumber;
    private Long amount;
}
