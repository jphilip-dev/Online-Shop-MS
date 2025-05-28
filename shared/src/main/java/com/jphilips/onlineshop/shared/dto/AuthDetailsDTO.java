package com.jphilips.onlineshop.shared.dto;

import java.util.List;

public record AuthDetailsDTO(Long id, String email, String name, List<String> roles) {
}