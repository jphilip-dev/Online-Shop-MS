package com.jphilips.onlineshop.item.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class AddToCartException extends AppException {
    public AddToCartException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public AddToCartException(ErrorCode errorCode) {
        super(errorCode);
    }
}
