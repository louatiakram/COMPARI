package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.RequestDto.UserRequestDto;
import com.FindMyPc.back.ResponseDto.UserResponseDto;
import com.FindMyPc.back.entity.User;
import com.FindMyPc.back.repository.UserRepository;
import com.FindMyPc.back.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(user -> modelMapper.map(user, UserResponseDto.class)).orElse(null);
    }

    @Override
    public UserResponseDto updateUser(int userId, UserRequestDto userRequestDto) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userRequestDto.getUsername());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());
            // set other fields if needed
            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }
        // Handle the case where the user does not exist
    }
}
