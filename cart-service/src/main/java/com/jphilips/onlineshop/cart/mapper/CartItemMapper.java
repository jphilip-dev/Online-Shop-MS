package com.jphilips.onlineshop.cart.mapper;

import com.jphilips.onlineshop.cart.dto.CartItemResponseDTO;
import com.jphilips.onlineshop.cart.entity.CartItem;
import com.jphilips.onlineshop.shared.dto.ItemResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemResponseDTO toDto(CartItem cartItem, ItemResponseDTO item){
        return CartItemResponseDTO.builder()
                .cartItemId(cartItem.getItemId())
                .count(cartItem.getCount())
                .item(item)
                .build();
    }
}
