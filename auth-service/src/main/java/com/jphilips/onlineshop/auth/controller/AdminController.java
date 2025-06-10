package com.jphilips.onlineshop.auth.controller;


import com.jphilips.onlineshop.auth.dto.AdminUpdateRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.admin.GetUsersServiceHandler;
import com.jphilips.onlineshop.auth.service.admin.UpdateUserAdminService;
import com.jphilips.onlineshop.shared.dto.ExceptionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Management", description = "Administrator APIs for managing users")
public class AdminController {

    private final GetUsersServiceHandler getUserServiceHandler;
    private final UpdateUserAdminService updateUserAdminService;

    @Operation(summary = "Get All Users",
            description = "Retrieve a list of all users in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getAllUsers());
    }


    @Operation(
            summary = "Get User by Email",
            description = "Retrieve a user by their email address"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @GetMapping("/users")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserByEmail(email));
    }

    @Operation(
            summary = "Get User by ID",
            description = "Retrieve a user by their unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserById(id));
    }

    @Operation(
            summary = "Update User by Admin",
            description = "Update user details as an administrator"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Email already exists", content = @Content()),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })
    @PutMapping("/users")
    public ResponseEntity<UserResponseDTO> updateUserById(@Valid @RequestBody AdminUpdateRequestDTO adminUpdateRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateUserAdminService.execute(adminUpdateRequestDTO));
    }

}

