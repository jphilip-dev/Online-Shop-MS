package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class UserPasswordMismatchException extends UserException {
    public UserPasswordMismatchException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserPasswordMismatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
