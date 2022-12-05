package com.battercodelab.guide.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    data_is_empty(404, "CODE001", "data is empty."),
    pwd_not_equal(409, "CODE002", "member is not equal pwd."),
    member_is_withdraw(409, "CODE003", "member is withdraw."),
    not_approve(403, "CODE004", "member is not approve."),
    conflict(409, "CODE005", "data is already exists.");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
