package com.jphilips.onlineshop.shared.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class AppException extends BaseException {
    public AppException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AppException(ErrorCode errorCode) {
        super(errorCode);
    }
}
