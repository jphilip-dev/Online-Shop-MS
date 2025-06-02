package com.jphilips.onlineshop.shared.util;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionResponseUtil {

    public static ExceptionResponseDTO createResponse(
            int status,
            String error,
            String message,
            String path,
            Map<String, String> fieldErrors) {

        return ExceptionResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .error(error)
                .message(message)
                .fieldErrors(fieldErrors)
                .path(path)
                .build();
    }
}