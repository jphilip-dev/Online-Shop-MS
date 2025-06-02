package com.jphilips.onlineshop.auth.dto;

public record UpdateUserCommand(String headerEmail, Long id, UserRequestDTO userRequestDTO) {
}
