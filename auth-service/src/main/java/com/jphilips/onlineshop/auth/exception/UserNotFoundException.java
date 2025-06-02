package com.jphilips.onlineshop.auth.exception;

import com.jphilips.onlineshop.shared.exception.ErrorCode;

public class UserNotFoundException extends UserException {
  public UserNotFoundException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }

  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
