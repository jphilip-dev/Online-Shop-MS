package com.jphilips.onlineshop.shared.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // === Standard HTTP Errors ===
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR_INTERNAL_SERVER"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ERROR_UNAUTHORIZED"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"ERROR_NOT_FOUND"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "ERROR_BAD_REQUEST"),

    // === Custom Application Errors ===
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ERROR_USER_NOT_FOUND"),
    USER_PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "ERROR_PASSWORD_MISMATCH"),
    USER_EXISTING_EMAIL(HttpStatus.BAD_REQUEST, "ERROR_EMAIL_ALREADY_EXISTS"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "ERROR_INVALID_INPUT"),

    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ERROR_ITEM_NOT_FOUND"),
    ITEM_EXISTING_SKU(HttpStatus.BAD_REQUEST, "ERROR_SKU_ALREADY_EXISTS");

    private final HttpStatus status;
    private final String messageCode;

}
