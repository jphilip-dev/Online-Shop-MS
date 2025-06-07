package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.exception.BaseExceptionHandler;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AuthExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponseDTO> handleJwtException(JwtException ex, WebRequest request){
        return buildResponseFrom(
                new AppException(ex instanceof ExpiredJwtException ? ErrorCode.JWT_EXPIRED : ErrorCode.JWT_INVALID),
                request
        );
    }

}