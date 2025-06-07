package com.jphilips.onlineshop.cart.dto;

import org.springframework.data.domain.Pageable;

public record GetAllCarItemsByUserQuery(Long userId, Pageable pageable) {
}
