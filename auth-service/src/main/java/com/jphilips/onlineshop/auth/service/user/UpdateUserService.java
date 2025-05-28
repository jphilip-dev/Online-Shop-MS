package com.jphilips.onlineshop.auth.service.user;


import com.jphilips.onlineshop.auth.dto.UpdateUserCommand;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.mapper.UserMapper;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements Command<UpdateUserCommand, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO execute(UpdateUserCommand updateUserCommand) {

        // Extract data
        var headerEmail = updateUserCommand.headerEmail();
        var id = updateUserCommand.id();
        var userRequestDTO = updateUserCommand.userRequestDTO();

        // Get save user details, this will throw exception if id doesn't exist
        var user = userServiceHelper.validateUserById(id);

        // Check ownership
        userServiceHelper.ownershipCheck(user.getEmail(), headerEmail);

        // Update fields
        user.setName(userRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        // Save user, not required but for readability
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}