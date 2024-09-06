package com.Compari.back.serviceImpl;

import com.Compari.back.RequestDto.ChangePasswordRequest;
import com.Compari.back.entity.User;
import com.Compari.back.service.UserService;
import com.Compari.back.RequestDto.UserRequestDto;
import com.Compari.back.ResponseDto.UserResponseDto;
import com.Compari.back.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto updateUser(int userId, UserRequestDto userRequestDto, Authentication authentication) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userRequestDto != null) {
                // Ensure this sets the correct value for username
                if (userRequestDto.getUsername() != null) {
                    user.setUsername(userRequestDto.getUsername());
                }
                if (userRequestDto.getEmail() != null) {
                    user.setEmail(userRequestDto.getEmail());
                }
                if (userRequestDto.getPassword() != null) {
                    user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
                }
            }

            User updatedUser = userRepository.save(user);
            return modelMapper.map(updatedUser, UserResponseDto.class);
        }
        return null;
    }


    @Override
    public void changePassword(ChangePasswordRequest request, Authentication authentication) {
        var user = (User) authentication.getPrincipal();

        // Check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        // Check if the new passwords match
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        /* Update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Save the new password
        userRepository.save(user);*/
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
    public void deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }
        // Handle the case where the user does not exist
    }
}
