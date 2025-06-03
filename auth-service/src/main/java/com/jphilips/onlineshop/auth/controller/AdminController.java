package com.jphilips.onlineshop.auth.controller;


import com.jphilips.onlineshop.auth.dto.AdminUpdateRequestDTO;
import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.admin.GetUsersServiceHandler;
import com.jphilips.onlineshop.auth.service.admin.UpdateUserAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final GetUsersServiceHandler getUserServiceHandler ;
    private final UpdateUserAdminService updateUserAdminService;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getAllUsers());
    }
    @GetMapping("/users")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserByEmail(email));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO>  getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserById(id));
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponseDTO>  updateUserById(@Valid @RequestBody AdminUpdateRequestDTO adminUpdateRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(updateUserAdminService.execute(adminUpdateRequestDTO));
    }

}

