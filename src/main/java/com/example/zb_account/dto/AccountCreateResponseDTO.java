package com.example.zb_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AccountCreateResponseDTO {
    public Long id;
    public String accountNumber;
    public LocalDateTime registeredAt;
}
