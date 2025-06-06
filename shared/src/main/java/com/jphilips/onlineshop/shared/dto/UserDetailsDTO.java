package com.jphilips.onlineshop.shared.dto;

public record UserDetailsDTO(
        Long userId,
        String userEmail,
        String userName) {
}
