package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import com.jphilips.onlineshop.shared.exception.custom.BaseException;
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
public class AuthExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()){
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return createExceptionResponse(new AppException(ErrorCode.BAD_REQUEST), fieldErrors, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherExceptions(Exception ex, WebRequest request){

        log.error("Unhandled error: {}", ex.getMessage(), ex);

        return createExceptionResponse(new AppException(ErrorCode.INTERNAL_SERVER_ERROR), request);
    }

    /*
     *
     *  Helper Method/s
     *
     */

    private ResponseEntity<ExceptionResponseDTO> createExceptionResponse(BaseException ex, Map<String, String> fieldErrors, WebRequest request){
        HttpStatus status = HttpStatus.valueOf(ex.getErrorCode().getStatus()); // add try catch later
        String messageCode = ex.getErrorCode().getMessageCode();

        var exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                messageCode,
                fieldErrors,
                request.getDescription(false).replace("uri=",""));

        return ResponseEntity.status(status).body(exceptionResponseDTO);
    }

    private ResponseEntity<ExceptionResponseDTO> createExceptionResponse(BaseException ex, WebRequest request){
        return this.createExceptionResponse(ex,null, request);
    }
}
