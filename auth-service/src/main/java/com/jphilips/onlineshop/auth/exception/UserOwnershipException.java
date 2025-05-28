package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class UserOwnershipException extends UserException {
    public UserOwnershipException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserOwnershipException(ErrorCode errorCode) {
        super(errorCode);
    }
}
