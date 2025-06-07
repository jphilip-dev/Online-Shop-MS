package com.jphilips.onlineshop.cart.dto;

import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import lombok.Builder;

@Builder
public record CartItemResponseDTO(
        Long cartItemId,
        Integer count,
        ItemResponseDTO item
) {
}
