package com.jphilips.onlineshop.auth.dto;

import com.jphilips.onlineshop.auth.validator.UniqueEmail;
import com.jphilips.onlineshop.shared.validator.groups.OnCreate;
import com.jphilips.onlineshop.shared.validator.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {

    @Email(groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @UniqueEmail(groups = OnCreate.class, message = "Email is already in use")
    private String email;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 6, groups = {OnCreate.class, OnUpdate.class}, message = "ERROR_MINIMUM_LENGTH_SIX_CHARS")
    private String password;

}
