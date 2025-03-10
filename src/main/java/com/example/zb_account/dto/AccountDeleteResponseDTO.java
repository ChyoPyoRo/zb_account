package com.example.zb_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDeleteResponseDTO {
    private Long userId;
    private String accountNumber;
    private String unRegisteredAt;
}
