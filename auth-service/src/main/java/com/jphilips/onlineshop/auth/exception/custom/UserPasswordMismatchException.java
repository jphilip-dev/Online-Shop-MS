package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class UserPasswordMismatchException extends AppException {
    public UserPasswordMismatchException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserPasswordMismatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
