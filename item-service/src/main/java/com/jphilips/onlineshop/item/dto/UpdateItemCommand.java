package com.jphilips.onlineshop.item.dto;

import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;

public record UpdateItemCommand(
        Long itemId,
        UserDetailsDTO userDetailsDTO,
        ItemRequestDTO itemRequestDTO) {
}
