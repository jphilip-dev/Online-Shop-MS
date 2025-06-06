package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class AuthExceptionHandler {

    private final ExceptionResponseService exceptionResponseService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        return exceptionResponseService.handle(new AppException(ErrorCode.BAD_REQUEST), ex.getBindingResult().getFieldErrors(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherExceptions(Exception ex, WebRequest request){

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return exceptionResponseService.handle(new AppException(ErrorCode.INTERNAL_SERVER_ERROR), request);
    }
}