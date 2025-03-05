package com.example.zb_account.controller;

import com.example.zb_account.dto.AccountCreateRequestDTO;
import com.example.zb_account.dto.AccountCreateResponseDTO;
import com.example.zb_account.service.AccountDetailService;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class AccountController {
    @Autowired
    private AccountDetailService accountDetailService;

    @PostMapping("/account")
    public ResponseEntity<AccountCreateResponseDTO> accountCreate(@RequestBody(required = false) AccountCreateRequestDTO accountCreateRequestDTO) {
        if(accountCreateRequestDTO == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        AccountCreateResponseDTO accountCreateResponseDTO = accountDetailService.createNewAccount(accountCreateRequestDTO);
        return ResponseEntity.ok(accountCreateResponseDTO);
    }
}
