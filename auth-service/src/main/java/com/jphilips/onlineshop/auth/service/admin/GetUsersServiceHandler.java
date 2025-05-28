package com.jphilips.onlineshop.auth.service.admin;

import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.mapper.UserMapper;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import com.jphilips.onlineshop.auth.service.user.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersServiceHandler {

    private final UserRepository userRepository;

    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;

    public UserResponseDTO getUserByEmail(String email){
        var user = userServiceHelper.validateUserByEmail(email);
        return userMapper.toDto(user);
    }

    private UserResponseDTO getUserById(Long id){
        var user = userServiceHelper.validateUserById(id);
        return userMapper.toDto(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

}
