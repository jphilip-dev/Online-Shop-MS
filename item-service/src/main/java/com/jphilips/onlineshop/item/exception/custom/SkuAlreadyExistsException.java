package com.jphilips.onlineshop.item.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class SkuAlreadyExistsException extends AppException {
    public SkuAlreadyExistsException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public SkuAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode);
    }
}
