package com.example.zb_account.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum CustomError {
    OVER_ACCOUNT("계좌 생성 가능 수량을 초과했습니다"),
    NO_USER_FOUND("해당 유저가 없습니다"),
    NO_PARAM("Parameter가 전달되지 않았습니다"),
    NO_BODY("Body값이 전달되지 않았습니다");

    private final String message;
}
