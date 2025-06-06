package com.jphilips.onlineshop.item.dto;

import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;

public record AddToCartCommand (
        UserDetailsDTO userDetailsDTO,
        Long itemId,
        Integer count
){
}
