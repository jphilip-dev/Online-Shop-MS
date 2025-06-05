package com.jphilips.onlineshop.item.mapper;

import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemResponseDTO toDto(Item item){
        return ItemResponseDTO.builder()
                .id(item.getId())
                .sku(item.getSku())
                .name(item.getName())
                .category(item.getCategory().toString())
                .description(item.getDescription())
                .stocks(item.getStocks())
                .price(item.getPrice())
                .brand(item.getBrand())
                .imageUrl(item.getImageUrl())
                .build();
    }
}
