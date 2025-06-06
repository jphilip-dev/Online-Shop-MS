package com.jphilips.onlineshop.item.exception.custom;

import com.jphilips.onlineshop.shared.exception.ErrorCode;
import com.jphilips.onlineshop.shared.exception.custom.AppException;

public class ItemOwnerMismatchException extends AppException {
  public ItemOwnerMismatchException(ErrorCode errorCode, String message) {
    super(errorCode, message);
  }

  public ItemOwnerMismatchException(ErrorCode errorCode) {
    super(errorCode);
  }
}
