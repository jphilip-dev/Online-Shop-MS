package com.jphilips.onlineshop.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ExceptionResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String value,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> fieldErrors,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<Long, String> itemErrors,
        String path) {
}
