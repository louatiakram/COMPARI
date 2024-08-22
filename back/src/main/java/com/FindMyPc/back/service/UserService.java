package com.FindMyPc.back.service;

import com.FindMyPc.back.RequestDto.ChangePasswordRequest;
import com.FindMyPc.back.RequestDto.UserRequestDto;
import com.FindMyPc.back.ResponseDto.UserResponseDto;
import java.security.Principal;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(int userId);

    UserResponseDto updateUser(int userId, UserRequestDto userRequestDto);
    
    void changePassword(ChangePasswordRequest request, Principal connectedUser);


    void deleteUser(int userId);
}
