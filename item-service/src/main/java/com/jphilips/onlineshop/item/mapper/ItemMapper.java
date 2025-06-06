package com.jphilips.onlineshop.item.mapper;

import com.jphilips.onlineshop.item.dto.ItemRequestDTO;
import com.jphilips.onlineshop.item.dto.ItemResponseDTO;
import com.jphilips.onlineshop.item.entity.Item;
import com.jphilips.onlineshop.item.enums.ItemCategory;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemResponseDTO toDto(Item item){
        return ItemResponseDTO.builder()
                .id(item.getId())
                .sellerId(item.getSellerId())
                .sellerEmail(item.getSellerEmail())
                .sellerName(item.getSellerName())
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

    public Item toEntity(ItemRequestDTO itemRequestDTO){
        return Item.builder()
                .sku(itemRequestDTO.getSku())
                .name(itemRequestDTO.getName())
                .category(ItemCategory.fromString(itemRequestDTO.getCategory()))
                .description(itemRequestDTO.getDescription())
                .stocks(itemRequestDTO.getStocks())
                .price(itemRequestDTO.getPrice())
                .brand(itemRequestDTO.getBrand())
                .imageUrl(itemRequestDTO.getImageUrl())
                .build();
    }
}
