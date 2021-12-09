package com.sparta.guide.service;

import com.sparta.guide.domain.User;
import com.sparta.guide.domain.UserRole;
import com.sparta.guide.dto.SignupRequestDto;
import com.sparta.guide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRole role = UserRole.USER;

        User user = new User(username, password, role);
        userRepository.save(user);

        return user;
    }
}
