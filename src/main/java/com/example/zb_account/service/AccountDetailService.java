package com.example.zb_account.service;

import com.example.zb_account.domain.Account;
import com.example.zb_account.domain.AccountUser;
import com.example.zb_account.dto.AccountCreateRequestDTO;
import com.example.zb_account.dto.AccountCreateResponseDTO;
import com.example.zb_account.dto.AccountDeleteResponseDTO;
import com.example.zb_account.dto.AccountGetResponseDTO;
import com.example.zb_account.repository.AccountDetailRepository;
import com.example.zb_account.repository.UserDetailRepository;
import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AccountDetailService {
    @Autowired
    private AccountDetailRepository accountDetailRepository;
    @Autowired
    private UserDetailRepository userDetailRepository;

    public List<AccountGetResponseDTO> getAllAccountOfThisUser(Long userId) {
        AccountUser currentAccountUser = userDetailRepository.findUserById(userId);
        if(currentAccountUser == null){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        Optional<List<Account>> foundAccount = accountDetailRepository.findAllByAccountName(currentAccountUser);
        if(foundAccount.isEmpty()){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        List<AccountGetResponseDTO> result = new ArrayList<AccountGetResponseDTO>();
        for(Account account : foundAccount.get()){
            result.add(new AccountGetResponseDTO(account.getAccountNumber(), account.getBalance()));
        }
        return result;
    }

    public AccountDeleteResponseDTO unRegisteredAccount(Long userId, String accountNumber) {
        AccountUser currentAccountUser = userDetailRepository.findUserById(userId);
        if(currentAccountUser == null){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        Optional<Account> foundAccount = accountDetailRepository.findValidAccount(accountNumber, currentAccountUser);
        if(foundAccount.isPresent()){
            accountDetailRepository.accountStatusUnRegistered(foundAccount.get());
        }else{
            throw new CustomException(CustomError.ACCOUNT_DELETE_FAIL);
        }
        return new AccountDeleteResponseDTO(foundAccount.get().getId(), foundAccount.get().getAccountNumber(),foundAccount.get().getAccountStatus().toString());
    }

    public AccountCreateResponseDTO createNewAccount(AccountCreateRequestDTO accountCreateRequestDTO ) {
        AccountUser currentAccountUser = userDetailRepository.findUserById(accountCreateRequestDTO.getUserId());
        if(currentAccountUser == null){
            throw new CustomException(CustomError.NO_USER_FOUND);
        }
        if(accountDetailRepository.accountMoreThenTen(currentAccountUser)){
            throw new CustomException(CustomError.OVER_ACCOUNT);
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
        Account newAccount = accountDetailRepository.createAccount(currentAccountUser, Long.toString(randomNumber), accountCreateRequestDTO.getInitBalance());

        return new AccountCreateResponseDTO(newAccount.getAccountUser().getId(), newAccount.getAccountNumber(), newAccount.getRegisteredAt());
    }
}
