package com.jphilips.onlineshop.auth.controller;

import com.jphilips.onlineshop.auth.dto.UpdateUserCommand;
import com.jphilips.onlineshop.auth.dto.UserRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.user.UpdateUserService;
import com.jphilips.onlineshop.shared.validator.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private UpdateUserService updateUserService;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody UserRequestDTO userRequestDTO) {

        var updatedUser = updateUserService.execute(new UpdateUserCommand(headerEmail, id, userRequestDTO));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);

    }
}
