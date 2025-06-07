package com.jphilips.onlineshop.auth.service.auth;


import com.jphilips.onlineshop.auth.dto.LoginRequestDTO;
import com.jphilips.onlineshop.auth.dto.TokenResponseDTO;
import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.exception.custom.MissingJwtException;
import com.jphilips.onlineshop.auth.exception.custom.UserPasswordMismatchException;
import com.jphilips.onlineshop.auth.service.user.CreateUserService;
import com.jphilips.onlineshop.auth.service.user.UserServiceHelper;
import com.jphilips.onlineshop.auth.util.JwtUtil;
import com.jphilips.onlineshop.shared.dto.AuthDetailsDTO;
import com.jphilips.onlineshop.shared.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final CreateUserService createUserService;
    private final UserServiceHelper userServiceHelper;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO authenticate(@Valid LoginRequestDTO loginRequestDTO) {
        var user = userServiceHelper.validateUserByEmail(loginRequestDTO.email());

        if(!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())){
            throw new UserPasswordMismatchException(ErrorCode.USER_PASSWORD_MISMATCH);
        }

        String token = jwtUtil.generateToken(user);

        return new TokenResponseDTO(token);
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO){
        return createUserService.execute(userRequestDTO);
    }

    public AuthDetailsDTO validateToken(String token) {
        if(token == null || !token.startsWith("Bearer ")){
            throw new MissingJwtException(ErrorCode.JWT_MISSING);
        }

        var claims = jwtUtil.validateToken(token.substring(7));

        String email = claims.get("email", String.class);
        Long id = claims.get("id", Long.class);
        String name = claims.get("name", String.class);
        List<?> rawRoles = claims.get("roles", List.class);

        List<String> roles = rawRoles.stream()
                .map(Object::toString)  // convert each element to String safely
                .toList();

        return new AuthDetailsDTO(id,email,name, roles);
    }
}
