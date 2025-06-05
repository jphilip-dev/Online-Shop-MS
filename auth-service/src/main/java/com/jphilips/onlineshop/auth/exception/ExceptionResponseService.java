package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ExceptionResponseHelper;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class ExceptionResponseService {

    public ResponseEntity<ExceptionResponseDTO> handle(BaseException ex, Map<String, String> errors, WebRequest request) {
        return ExceptionResponseHelper.createExceptionResponse(ex, errors, request);
    }

    public ResponseEntity<ExceptionResponseDTO> handle(BaseException ex, WebRequest request) {
        return ExceptionResponseHelper.createExceptionResponse(ex, null, request);
    }
}
