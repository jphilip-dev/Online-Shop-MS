package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class UserException extends BaseException {

    public UserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
