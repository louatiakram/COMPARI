package com.FindMyPc.back.service;

import com.FindMyPc.back.RequestDto.ChangePasswordRequest;
import com.FindMyPc.back.RequestDto.UserRequestDto;
import com.FindMyPc.back.ResponseDto.UserResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(int userId);

    UserResponseDto updateUser(int userId, UserRequestDto userRequestDto, Authentication authentication);
    
    void changePassword(ChangePasswordRequest request, Authentication authentication);

    void deleteUser(int userId);
}
