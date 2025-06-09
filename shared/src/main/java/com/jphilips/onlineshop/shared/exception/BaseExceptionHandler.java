package com.jphilips.onlineshop.shared.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
import com.jphilips.onlineshop.shared.exception.custom.CheckOutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class BaseExceptionHandler {

    @ExceptionHandler(CheckOutException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCheckOutException(CheckOutException ex, WebRequest request){
        return buildResponseFrom(
                ex,
                ex.getItemErrors(),
                request
        );
    }


    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAppException(AppException ex, WebRequest request){
        return buildResponseFrom(
                ex,
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        return buildResponseFrom(
                new AppException(ErrorCode.BAD_REQUEST),
                ex.getBindingResult().getFieldErrors(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherExceptions(Exception ex, WebRequest request){

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return buildResponseFrom(
                new AppException(ErrorCode.INTERNAL_SERVER_ERROR),
                request
        );
    }

    protected ResponseEntity<ExceptionResponseDTO> buildResponseFrom(BaseException ex, WebRequest request) {
        return buildResponseFrom(ex, null,null, request);
    }
    protected ResponseEntity<ExceptionResponseDTO> buildResponseFrom(BaseException ex,List<FieldError> fieldErrorsList, WebRequest request) {
        return buildResponseFrom(ex, fieldErrorsList,null, request);

    }
    protected ResponseEntity<ExceptionResponseDTO> buildResponseFrom(BaseException ex,Map<Long, String> itemErrors, WebRequest request) {
        return buildResponseFrom(ex, null,itemErrors, request);

    }

    protected ResponseEntity<ExceptionResponseDTO> buildResponseFrom(
            BaseException ex,
            List<FieldError> fieldErrorsList,
            Map<Long, String> itemErrors,
            WebRequest request) {

        HttpStatus status = ex.getErrorCode().getStatus();

        Map<String, String> fieldErrors = null; // null is handled by JsonInclude

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
                ex.getMessage() == null || ex.getMessage().isBlank() ? null : ex.getMessage(),
                fieldErrors,
                itemErrors,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(status).body(response);
    }
}
