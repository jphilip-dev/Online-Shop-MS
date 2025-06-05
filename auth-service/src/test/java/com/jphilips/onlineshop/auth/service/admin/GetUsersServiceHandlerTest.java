package com.jphilips.onlineshop.auth.service.admin;

import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.entity.Role;
import com.jphilips.onlineshop.auth.entity.User;
import com.jphilips.onlineshop.auth.mapper.UserMapper;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import com.jphilips.onlineshop.auth.service.user.UserServiceHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class GetUsersServiceHandlerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceHelper userServiceHelper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private GetUsersServiceHandler getUsersServiceHandler;

    private User createUser(Long id, String email, String name) {
        return User.builder()
                .id(id)
                .email(email)
                .name(name)
                .password("secret")
                .isActive(true)
                .roles(Set.of(new Role("USER", "User role")))
                .build();
    }

    private UserResponseDTO createUserDto(Long id, String email, String name) {
        return UserResponseDTO.builder()
                .id(id)
                .email(email)
                .name(name)
                .isActive(true)
                .roles(List.of("USER"))
                .build();
    }

    @Test
    void testGetUserByEmail() {
        String email = "john@example.com";
        User mockUser = createUser(1L, email, "John Doe");
        UserResponseDTO mockDto = createUserDto(1L, email, "John Doe");

        when(userServiceHelper.validateUserByEmail(anyString())).thenReturn(mockUser);
        when(userMapper.toDto(any(User.class))).thenReturn(mockDto);

        UserResponseDTO result = getUsersServiceHandler.getUserByEmail(email);

        assertEquals(mockDto, result);
        verify(userServiceHelper).validateUserByEmail(email);
        verify(userMapper).toDto(mockUser);
    }

    @Test
    void testGetUserById() {
        Long id = 1L;
        User mockUser = createUser(id, "john@example.com", "John Doe");
        UserResponseDTO mockDto = createUserDto(id, "john@example.com", "John Doe");

        when(userServiceHelper.validateUserById(anyLong())).thenReturn(mockUser);
        when(userMapper.toDto(any(User.class))).thenReturn(mockDto);

        UserResponseDTO result = getUsersServiceHandler.getUserById(id);

        assertEquals(mockDto, result);
        verify(userServiceHelper).validateUserById(id);
        verify(userMapper).toDto(mockUser);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(
                createUser(1L, "john1@example.com", "John One"),
                createUser(2L, "john2@example.com", "John Two")
        );
        List<UserResponseDTO> dtos = List.of(
                createUserDto(1L, "john1@example.com", "John One"),
                createUserDto(2L, "john2@example.com", "John Two")
        );

        when(userRepository.findAll()).thenReturn(users);
        // Return matching DTO for each user input dynamically:
        when(userMapper.toDto(any(User.class))).thenAnswer(invocation -> {
            User argUser = invocation.getArgument(0);
            return dtos.stream()
                    .filter(dto -> dto.id().equals(argUser.getId()))
                    .findFirst()
                    .orElse(null);
        });

        List<UserResponseDTO> result = getUsersServiceHandler.getAllUsers();

        assertEquals(dtos, result);
        verify(userRepository).findAll();
        verify(userMapper, times(users.size())).toDto(any(User.class));
    }
}

