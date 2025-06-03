package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class UserOwnershipException extends AppException {
    public UserOwnershipException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserOwnershipException(ErrorCode errorCode) {
        super(errorCode);
    }
}
