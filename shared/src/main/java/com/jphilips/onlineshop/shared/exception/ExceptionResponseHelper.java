package com.jphilips.onlineshop.shared.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExceptionResponseHelper {

    public static ResponseEntity<ExceptionResponseDTO> createExceptionResponse(
            BaseException ex,
            List<FieldError> fieldErrorsList,
            WebRequest request) {

        HttpStatus status = ex.getErrorCode().getStatus();

        Map<String, String> fieldErrors = null;

        if (fieldErrorsList != null){
            fieldErrors = new HashMap<>();
            for (FieldError fieldError : fieldErrorsList){
                fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        }

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
