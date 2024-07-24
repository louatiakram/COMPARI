package com.FindMyPc.back.service;

import java.util.List;

import com.FindMyPc.back.RequestDto.UserRequestDto;
import com.FindMyPc.back.ResponseDto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(int userId);
    UserResponseDto updateUser(int userId, UserRequestDto userRequestDto);
    void deleteUser(int userId);
}
