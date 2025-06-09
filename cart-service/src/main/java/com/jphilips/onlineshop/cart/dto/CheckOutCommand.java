package com.jphilips.onlineshop.cart.dto;

import java.util.List;

public record CheckOutCommand(
        Long userId,
        List<Long> itemIds
) {
}
