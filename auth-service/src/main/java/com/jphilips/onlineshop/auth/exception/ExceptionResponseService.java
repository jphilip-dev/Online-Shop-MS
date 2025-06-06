package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ExceptionResponseHelper;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Component
public class ExceptionResponseService {

    public ResponseEntity<ExceptionResponseDTO> handle(BaseException ex, List<FieldError> fieldErrors, WebRequest request) {
        return ExceptionResponseHelper.buildResponseFrom(ex, fieldErrors, request);
    }

    public ResponseEntity<ExceptionResponseDTO> handle(BaseException ex, WebRequest request) {
        return ExceptionResponseHelper.buildResponseFrom(ex, null, request);
    }
}
