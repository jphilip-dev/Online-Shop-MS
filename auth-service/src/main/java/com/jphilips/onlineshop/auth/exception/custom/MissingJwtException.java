package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class MissingJwtException extends BaseException {
    public MissingJwtException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public MissingJwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
