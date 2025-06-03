package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class ExistingEmailException extends UserException {
    public ExistingEmailException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ExistingEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
