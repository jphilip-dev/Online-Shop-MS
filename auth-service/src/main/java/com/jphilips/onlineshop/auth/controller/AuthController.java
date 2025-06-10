package com.jphilips.onlineshop.auth.controller;


import com.jphilips.onlineshop.auth.dto.LoginRequestDTO;
import com.jphilips.onlineshop.auth.dto.TokenResponseDTO;
import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.auth.AuthService;
import com.jphilips.onlineshop.shared.dto.AuthDetailsDTO;
import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication and registration")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "User Login",
            description = "Authenticate user and return JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Login successful",
                    content = @Content(schema = @Schema(implementation = TokenResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.accepted().body(authService.authenticate(loginRequestDTO));
    }

    @Operation(
            summary = "User Registration",
            description = "Register a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or email already exists", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Validated(OnCreate.class) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequestDTO));
    }

    @Operation(
            summary = "Validate Token",
            description = "Validate JWT token and return authentication details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token valid",
                    content = @Content(schema = @Schema(implementation = AuthDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid or missing token", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @GetMapping("/validate")
    public ResponseEntity<AuthDetailsDTO> validateToken(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok().body(authService.validateToken(token));
    }

}