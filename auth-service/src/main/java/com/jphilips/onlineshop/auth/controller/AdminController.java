package com.jphilips.onlineshop.auth.controller;


import com.jphilips.onlineshop.auth.dto.UserResponseDTO;
import com.jphilips.onlineshop.auth.service.admin.GetUsersServiceHandler;
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

    @GetMapping("/all-users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getAllUsers());
    }
    @GetMapping("/user")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserByEmail(email));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO>  getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(getUserServiceHandler.getUserById(id));
    }

}

