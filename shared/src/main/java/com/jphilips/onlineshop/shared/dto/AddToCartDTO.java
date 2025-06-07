package com.jphilips.onlineshop.shared.dto;

public record AddToCartDTO(
        Long userId,
        String userEmail,
        String userName,
        Long itemId,
        Integer count) {
}
