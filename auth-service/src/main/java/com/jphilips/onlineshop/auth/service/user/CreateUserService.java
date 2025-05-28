package com.jphilips.onlineshop.auth.service.user;


import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.entity.Role;
import com.jphilips.onlineshop.auth.mapper.UserMapper;
import com.jphilips.onlineshop.auth.repository.RoleRepository;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import com.jphilips.onlineshop.shared.util.Command;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements Command<UserRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "USER";


    // DEV purposes, will use data.sql to load data
    @PostConstruct
    private void init() {
        if (roleRepository.findById(USER_ROLE).isEmpty()) {
            var newRole = new Role(USER_ROLE, "NORMAL USER");
            roleRepository.save(newRole);
        }
    }

    @Override
    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {

        // Map dto to entity
        var newUser = userMapper.toEntity(userRequestDTO);

        // encrypt password
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // set to Inactive
        newUser.setIsActive(true); // dev

        // Add role
        Role role = roleRepository.findById(USER_ROLE)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + USER_ROLE));

        newUser.addRole(role);

        // Save
        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }
}