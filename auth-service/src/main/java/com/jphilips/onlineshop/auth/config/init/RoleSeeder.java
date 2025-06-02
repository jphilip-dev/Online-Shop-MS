package com.jphilips.onlineshop.auth.config.init;

import com.jphilips.onlineshop.auth.entity.Role;
import com.jphilips.onlineshop.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void seedAdminRole() {
        if (roleRepository.findById("ADMIN").isEmpty()) {
            roleRepository.save(new Role("ADMIN", "Admin User Role"));
        }
        if (roleRepository.findById("USER").isEmpty()) {
            roleRepository.save(new Role("USER", "Normal User Role"));
        }
    }
}