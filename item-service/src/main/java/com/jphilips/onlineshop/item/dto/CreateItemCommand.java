package com.jphilips.onlineshop.item.dto;

public record CreateItemCommand(Long userId, String userEmail, String userName, ItemRequestDTO itemRequestDTO) {
}
