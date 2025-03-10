package com.example.zb_account.dto;


import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreateRequestDTO  {
    public Long userId;
    public Long initBalance;

    public void validate() {
        if(userId == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        if(initBalance == null || initBalance < 0) {
            throw new CustomException(CustomError.NO_BODY);
        }
    }
}
