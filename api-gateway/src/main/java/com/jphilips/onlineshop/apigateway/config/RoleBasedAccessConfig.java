package com.jphilips.onlineshop.apigateway.config;

import java.util.List;

public class RoleBasedAccessConfig {
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}