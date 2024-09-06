package com.Compari.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private int userID;
    private String username;
    private String email;
    private String image;
    private Set<RoleResponseDto> roles;
}