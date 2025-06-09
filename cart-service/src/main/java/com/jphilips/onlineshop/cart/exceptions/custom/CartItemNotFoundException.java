package com.jphilips.onlineshop.cart.exceptions.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class CartItemNotFoundException extends AppException {
    public CartItemNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public CartItemNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
