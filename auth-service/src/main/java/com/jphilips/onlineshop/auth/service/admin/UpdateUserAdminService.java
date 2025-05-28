package com.jphilips.onlineshop.auth.service.admin;


import com.jphilips.onlineshop.auth.dto.AdminUpdateRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.mapper.UserMapper;
import com.jphilips.onlineshop.auth.repository.RoleRepository;
import com.jphilips.onlineshop.auth.repository.UserRepository;
import com.jphilips.onlineshop.auth.service.user.UserServiceHelper;
import com.jphilips.onlineshop.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserAdminService implements Command<AdminUpdateRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserServiceHelper userServiceHelper;
    private final UserMapper userMapper;

    private static final String ADMIN_ROLE = "ADMIN";

    @Override
    public UserResponseDTO execute(AdminUpdateRequestDTO adminUpdateRequestDTO) {

        // Extract data
        var id = adminUpdateRequestDTO.getId();

        // Get save user details, this will throw exception if id doesn't exist
        var user = userServiceHelper.validateUserById(id);

        // Update fields
        user.setEmail(adminUpdateRequestDTO.getEmail());
        user.setName(adminUpdateRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(adminUpdateRequestDTO.getPassword()));
        user.setIsActive(adminUpdateRequestDTO.getIsActive());

        if (adminUpdateRequestDTO.getIsAdmin()) {
            var adminRole = roleRepository.findById(ADMIN_ROLE).orElseThrow();
            if (!user.getRoles().contains(adminRole)) {
                user.addRole(adminRole);
            }
        }

        // Save user, not required but for readability
        userRepository.save(user);

        return userMapper.toDto(user);
    }
}