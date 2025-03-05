package com.example.zb_account.dto;


import com.example.zb_account.type.CustomError;
import com.example.zb_account.util.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreateRequestDTO {
    public Long id;
    public Long money;

    public void validate() {
        if(id == null) {
            throw new CustomException(CustomError.NO_BODY);
        }
        if(money == null || money < 0) {
            throw new CustomException(CustomError.NO_BODY);
        }
    }
}
