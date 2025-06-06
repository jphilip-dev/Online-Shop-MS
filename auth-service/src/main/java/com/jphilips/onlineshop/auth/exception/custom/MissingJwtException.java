package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;

public class MissingJwtException extends AppException {
    public MissingJwtException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public MissingJwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
