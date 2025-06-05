package com.jphilips.onlineshop.shared.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionResponseHelper {

    public static ResponseEntity<ExceptionResponseDTO> createExceptionResponse(
            BaseException ex,
            Map<String, String> fieldErrors,
            WebRequest request) {

        HttpStatus status = ex.getErrorCode().getStatus();

        var response = new ExceptionResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getErrorCode().getMessageCode(),
                fieldErrors,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(status).body(response);
    }
}
