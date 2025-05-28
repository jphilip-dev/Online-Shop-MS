package com.jphilips.onlineshop.auth.dto;

import com.jphilips.onlineshop.shared.exception.FieldErrorMessage;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
import com.jphilips.onlineshop.shared.validator.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @Email(message = "ERROR_INVALID_EMAIL")
        @NotBlank(message = "ERROR_BLANK_EMAIL")
        String email,
        @NotBlank
        @Size(min = 6, groups = {OnCreate.class, OnUpdate.class}, message = "ERROR_MIN_6_CHARS")
        String password) {
}
