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
public class TransactionUseRequestDTO implements AccountLockIdInterface {
    private Long userId;
    private String accountNumber;
    private Long amount;
}
