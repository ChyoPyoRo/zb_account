package com.example.zb_account.service;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import com.example.zb_account.domain.Transaction;
import com.example.zb_account.dto.*;
import com.example.zb_account.repository.AccountDetailRepository;
import com.example.zb_account.repository.TransactionDetailRepository;
import com.example.zb_account.repository.UserDetailRepository;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.type.TransactionResultType;
import com.example.zb_account.type.TransactionType;
import com.example.zb_account.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionDetailService {
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;
    @Autowired
    private AccountDetailRepository accountDetailRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    public TransactionuseResponseDTO useTransaction(TransactionUseRequestDTO transactionUseRequestDTO) {
        AccountUser currentAccountUser = userDetailRepository.findUserById(transactionUseRequestDTO.getUserId());
        if(currentAccountUser == null){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        Optional<Account> currentAccount = accountDetailRepository.findValidAccount(transactionUseRequestDTO.getAccountNumber(), currentAccountUser);
        if(!currentAccount.isPresent()){
            //해당 계정에 해당 계좌 없음, 계좌가 이미 해지 상태,
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        if(currentAccount.get().getBalance() < transactionUseRequestDTO.getAmount()){
            //잔액보다 요청량이 많음
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        currentAccount.get().useBalance(transactionUseRequestDTO.getAmount());
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.USE)
                .transactionResultType(TransactionResultType.success)
                .account(currentAccount.get())
                .amount(transactionUseRequestDTO.getAmount())
                .balanceSnapshot(currentAccount.get().getBalance())
                .transactionId(UUID.randomUUID().toString().replace("-", ""))
                .createdAt(LocalDateTime.now())
                .build();
        Transaction result = transactionDetailRepository.save(transaction);
        return result.toUseResponseDTO();
    }

    public TransactionCancelResponseDTO cancelTransaction(TransactionCancelRequestDTO transactionCancelRequestDTO) {
        //TransactionId로 해당 Transaction을 찾아야함
        Optional<Transaction> useTransaction = transactionDetailRepository.findByTransactionId(transactionCancelRequestDTO.getTransactionId());
        if(useTransaction.isEmpty()){
            //해당 거래 내역 없음
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        Transaction transaction = useTransaction.get();
        if(!Objects.equals(transactionCancelRequestDTO.getAccountNumber(), transaction.getAccount().getAccountNumber())){
            //계좌번호 오류
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        if(!Objects.equals(transactionCancelRequestDTO.getAmount(), transaction.getAmount())){
            //일부 취소 불가
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        if(transaction.getCreatedAt().isBefore(LocalDateTime.now().minusYears(1))){
            //1년 지난 거래 취소 불가
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        transaction.getAccount().setBalance(transactionCancelRequestDTO.getAmount());
        Transaction cancelTransaction = Transaction.builder()
                .transactionType(TransactionType.CANCEL)
                .transactionResultType(TransactionResultType.success)
                .account(transaction.getAccount())
                .amount(transactionCancelRequestDTO.getAmount())
                .balanceSnapshot(transaction.getAccount().getBalance())
                .transactionId(UUID.randomUUID().toString().replace("-", ""))
                .createdAt(LocalDateTime.now())
                .build();
        transactionDetailRepository.save(cancelTransaction);
        return cancelTransaction.toCancelResponseDTO();
    }

    public TransactionGetResponseDTO getTransaction(String transactionId) {
        Optional<Transaction> currentTransaction = transactionDetailRepository.findByTransactionId(transactionId);
        if(currentTransaction.isEmpty()){
            throw new CustomException(CustomError.TRANSACTION_ERROR);
        }
        return currentTransaction.get().toGetResponseDTO();
    }
}
