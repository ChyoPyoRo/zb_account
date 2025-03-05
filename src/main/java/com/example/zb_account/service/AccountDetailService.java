package com.example.zb_account.service;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import com.example.zb_account.dto.AccountCreateRequestDTO;
import com.example.zb_account.dto.AccountCreateResponseDTO;
import com.example.zb_account.repository.AccountDetailRepository;
import com.example.zb_account.repository.UserDetailRepository;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


@Service
public class AccountDetailService {
    @Autowired
    private AccountDetailRepository accountDetailRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    public AccountCreateResponseDTO createNewAccount(AccountCreateRequestDTO accountCreateRequestDTO ) {
        AccountUser currentAccountUser = userDetailRepository.findUserById(accountCreateRequestDTO.getId());
        if(currentAccountUser == null){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        SecureRandom random = new SecureRandom();
        long randomNumber = 0L;
        while(true){
            //중복체크
            randomNumber = 1_000_000_000L + random.nextLong(9_000_000_000L);
            if(accountDetailRepository.getAccount(Long.toString(randomNumber))){
                break;
            }
        }
        Account newAccount = accountDetailRepository.createAccount(currentAccountUser, Long.toString(randomNumber), accountCreateRequestDTO.getMoney());

        return new AccountCreateResponseDTO(newAccount.getAccountUser().getId(), newAccount.getAccountNumber(), newAccount.getRegisteredAt());
    }
}
