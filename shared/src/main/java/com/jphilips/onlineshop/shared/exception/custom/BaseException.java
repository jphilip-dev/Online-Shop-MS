package com.jphilips.onlineshop.shared.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;

    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode){
        super();
        this.errorCode = errorCode;
    }
}
