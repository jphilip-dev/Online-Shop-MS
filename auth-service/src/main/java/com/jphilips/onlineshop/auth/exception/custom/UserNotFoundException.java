package com.jphilips.onlineshop.auth.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class UserNotFoundException extends AppException {
  public UserNotFoundException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }

  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
