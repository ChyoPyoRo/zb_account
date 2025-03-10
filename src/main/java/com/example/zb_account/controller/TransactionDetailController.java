package com.example.zb_account.controller;


import com.example.zb_account.aop.AccountLock;
import com.example.zb_account.dto.*;
import com.example.zb_account.service.TransactionDetailService;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Transactional
public class TransactionDetailController {
    @Autowired
    private TransactionDetailService transactionDetailService;

    @PostMapping("/transaction/use")
    @AccountLock
    public ResponseEntity<?> useTransaction(@RequestBody TransactionUseRequestDTO request) {
        if(request == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        TransactionuseResponseDTO transactionuseResponseDTO = transactionDetailService.useTransaction(request);
        return ResponseEntity.ok(transactionuseResponseDTO);
    }

    @PostMapping("/transaction/cancel")
    @AccountLock
    public ResponseEntity<?> deleteTransaction(@RequestBody TransactionCancelRequestDTO request) throws InterruptedException {
        if(request == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        TransactionCancelResponseDTO transactionCancelResponseDTO = transactionDetailService.cancelTransaction(request);
        return ResponseEntity.ok(transactionCancelResponseDTO);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable String transactionId) {
        if(transactionId == null) {
            throw new CustomException(CustomError.NO_PARAM);
        }
        TransactionGetResponseDTO transactionGetResponseDTO = transactionDetailService.getTransaction(transactionId);
        return ResponseEntity.ok(transactionGetResponseDTO);
    }
}
