package com.jphilips.onlineshop.item.dto;

import com.jphilips.onlineshop.shared.dto.UserDetailsDTO;

public record CreateItemCommand(
        UserDetailsDTO userDetailsDTO,
        ItemRequestDTO itemRequestDTO) {
}
