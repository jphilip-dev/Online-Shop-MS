package com.jphilips.onlineshop.shared.dto;

import java.util.List;
import java.util.Map;

public record CheckoutDTO(
        Long userId,
        Map<Long, Integer> itemAndCount
) {
}
