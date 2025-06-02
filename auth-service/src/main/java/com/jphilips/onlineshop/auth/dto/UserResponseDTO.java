package com.jphilips.onlineshop.auth.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponseDTO(Long id,
                              String email,
                              String name,
                              Boolean isActive,
                              List<String> roles) {
}
