package com.example.zb_account.controller;

import com.example.zb_account.dto.AccountCreateRequestDTO;
import com.example.zb_account.dto.AccountCreateResponseDTO;
import com.example.zb_account.dto.AccountDeleteResponseDTO;
import com.example.zb_account.dto.AccountGetResponseDTO;
import com.example.zb_account.service.AccountDetailService;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Transactional
public class AccountController {
    @Autowired
    private AccountDetailService accountDetailService;

    @GetMapping("/account")
    public ResponseEntity<?> accountGet(@RequestParam Long  userId) {
        if (userId == null) {
            throw new CustomException(CustomError.NO_PARAM);
        }

        List<AccountGetResponseDTO> result = accountDetailService.getAllAccountOfThisUser(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/account")
    public ResponseEntity<AccountCreateResponseDTO> accountCreate(@RequestBody(required = false) AccountCreateRequestDTO accountCreateRequestDTO) {
        if(accountCreateRequestDTO == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        log.debug("accountCreateRequestDTO: {}", accountCreateRequestDTO);
        AccountCreateResponseDTO accountCreateResponseDTO = accountDetailService.createNewAccount(accountCreateRequestDTO);
        return ResponseEntity.ok(accountCreateResponseDTO);
    }

    @DeleteMapping("/account")
    public ResponseEntity<AccountDeleteResponseDTO> accountDelete(@RequestParam(required = false) Long userId, @RequestParam(required = false) String accountNumber) {
        if(userId == null || accountNumber == null) {
            throw new CustomException(CustomError.NO_PARAM);
        }
        AccountDeleteResponseDTO accountDeleteResponseDTO = accountDetailService.unRegisteredAccount(userId, accountNumber);
        return ResponseEntity.ok(accountDeleteResponseDTO);
    }
}
