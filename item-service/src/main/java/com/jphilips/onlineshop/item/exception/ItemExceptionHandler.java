package com.jphilips.onlineshop.item.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ItemExceptionHandler {

    private final ExceptionResponseService exceptionResponseService;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAppException(AppException ex, WebRequest request){
        return exceptionResponseService.handle(
                ex,
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        return exceptionResponseService.handle(
                new AppException(ErrorCode.BAD_REQUEST),
                ex.getBindingResult().getFieldErrors(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherExceptions(Exception ex, WebRequest request){

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return exceptionResponseService.handle(
                new AppException(ErrorCode.INTERNAL_SERVER_ERROR),
                request
        );
    }
}