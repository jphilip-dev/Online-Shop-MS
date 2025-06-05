package com.jphilips.onlineshop.item.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemResponseDTO(
        Long id,
        String sku,
        String name,
        String category,
        String description,
        Integer stocks,
        BigDecimal price,
        String brand,
        String imageUrl
) {}