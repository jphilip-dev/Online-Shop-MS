package com.jphilips.onlineshop.item.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class ItemNotFoundException extends AppException {
    public ItemNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ItemNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
