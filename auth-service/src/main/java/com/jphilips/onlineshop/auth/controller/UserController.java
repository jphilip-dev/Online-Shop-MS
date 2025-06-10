package com.jphilips.onlineshop.auth.controller;

import com.jphilips.onlineshop.auth.dto.UpdateUserCommand;
import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.user.UpdateUserService;
import com.jphilips.onlineshop.shared.validator.groups.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for user profile management")
public class UserController {

    private UpdateUserService updateUserService;

    @Operation(
            summary = "Update User Profile",
            description = "Update the profile of the authenticated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized or ownership mismatch",content = @Content()),
            @ApiResponse(responseCode = "404", description = "User not found",content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody UserRequestDTO userRequestDTO) {

        var updatedUser = updateUserService.execute(new UpdateUserCommand(headerEmail, id, userRequestDTO));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);

    }
}
