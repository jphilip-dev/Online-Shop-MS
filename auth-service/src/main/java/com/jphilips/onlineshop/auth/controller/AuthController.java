package com.jphilips.onlineshop.auth.controller;


import com.jphilips.onlineshop.auth.dto.LoginRequestDTO;
import com.jphilips.onlineshop.auth.dto.TokenResponseDTO;
import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.auth.AuthService;
import com.jphilips.onlineshop.shared.dto.AuthDetailsDTO;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.accepted().body(authService.authenticate(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Validated(OnCreate.class) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequestDTO));
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthDetailsDTO> validateToken(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok().body(authService.validateToken(token));
    }

}