package com.jphilips.onlineshop.item.dto;

import jakarta.persistence.Column;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemResponseDTO(
        Long id,
        Long sellerId,
        String sellerName,
        String sellerEmail,
        String sku,
        String name,
        String category,
        String description,
        Integer stocks,
        BigDecimal price,
        String brand,
        String imageUrl
) {
}