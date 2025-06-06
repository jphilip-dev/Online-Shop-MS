package com.jphilips.onlineshop.item.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class ItemStockException  extends AppException {
    public ItemStockException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ItemStockException(ErrorCode errorCode) {
        super(errorCode);
    }
}
