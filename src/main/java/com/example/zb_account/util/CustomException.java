package com.example.zb_account.util;

import com.example.zb_account.type.CustomError;

public class CustomException extends RuntimeException {
    private final CustomError error;
    public CustomException(CustomError error) {
        super(error.getMessage());
        this.error = error;
    }
}
