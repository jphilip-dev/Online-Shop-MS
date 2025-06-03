package com.jphilips.onlineshop.shared.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    // === Standard HTTP Errors ===
    INTERNAL_SERVER_ERROR(500, "ERROR_INTERNAL_SERVER"),
    UNAUTHORIZED(401, "ERROR_UNAUTHORIZED"),
    NOT_FOUND(404, "ERROR_NOT_FOUND"),
    BAD_REQUEST(400, "ERROR_BAD_REQUEST"),

    // === Custom Application Errors ===
    USER_NOT_FOUND(400, "ERROR_USER_NOT_FOUND"),
    USER_PASSWORD_MISMATCH(401, "ERROR_PASSWORD_MISMATCH"),
    USER_EXISTING_EMAIL(400, "ERROR_EMAIL_ALREADY_EXISTS"),
    INVALID_INPUT(400, "ERROR_INVALID_INPUT");

    private final int status;
    private final String messageCode;

}
