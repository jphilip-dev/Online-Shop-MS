package com.jphilips.onlineshop.auth.repository;

import com.jphilips.onlineshop.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
