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
    NO_BODY("Body값이 전달되지 않았습니다"),
    NO_ACCOUNT_FOUND("계좌가 없습니다"),
    TRANSACTION_ERROR("처리 중 에러"),
    INVALID_REQUEST("잘못된 요청"),
    OVER_AMOUNT("잔액보다 많은 비용을 사용하려 했습니다."),
    AOP_LOCK("해당 계좌는 사용중입니다"),
    ACCOUNT_DELETE_FAIL("계좌 해지중 에러가 발생했습니다");

    private final String message;
}
