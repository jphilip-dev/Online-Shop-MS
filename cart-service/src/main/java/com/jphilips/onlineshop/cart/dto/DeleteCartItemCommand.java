package com.jphilips.onlineshop.cart.dto;

public record DeleteCartItemCommand(
        Long userId,
        Long cartItemId) {
}
