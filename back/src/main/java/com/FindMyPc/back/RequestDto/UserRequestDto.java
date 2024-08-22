package com.FindMyPc.back.RequestDto;


import com.FindMyPc.back.entity.Role;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotEmpty(message = "{User.username.invalid}")
    @NotNull(message = "{User.username.invalid}")
    @NotBlank(message = "{User.username.invalid}")
    @Size(min = 3, message = "Username should be a minimum of 3 characters")
    private String username;

    @Email(message = "{User.email.invalid}")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "{User.password.invalid}")
    private String password;

    private String image;
    private Role role;

}