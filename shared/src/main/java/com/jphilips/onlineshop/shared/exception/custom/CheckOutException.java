package com.jphilips.onlineshop.shared.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class CheckOutException extends AppException {

    private Map<Long, String> itemErrors;

    public CheckOutException(ErrorCode errorCode, String message, Map<Long, String> itemErrors) {
        super(errorCode, message);
        this.itemErrors =  itemErrors;
    }

    public CheckOutException(ErrorCode errorCode, Map<Long, String> itemErrors) {
        super(errorCode);
        this.itemErrors =  itemErrors;
    }
}
