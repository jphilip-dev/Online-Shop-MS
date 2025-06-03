package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class ExistingEmailException extends AppException {
    public ExistingEmailException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ExistingEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
