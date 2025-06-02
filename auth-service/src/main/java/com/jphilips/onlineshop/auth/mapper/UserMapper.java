package com.jphilips.onlineshop.auth.mapper;

import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.entity.Role;
import com.jphilips.onlineshop.auth.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .isActive(user.getIsActive())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .toList())
                .build();
    }

    public User toEntity(UserRequestDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }
}
