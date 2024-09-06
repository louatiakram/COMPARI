package com.Compari.back.service;

import com.Compari.back.RequestDto.ChangePasswordRequest;
import com.Compari.back.RequestDto.UserRequestDto;
import com.Compari.back.ResponseDto.UserResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(int userId);

    UserResponseDto updateUser(int userId, UserRequestDto userRequestDto, Authentication authentication);
    
    void changePassword(ChangePasswordRequest request, Authentication authentication);

    void deleteUser(int userId);
}
