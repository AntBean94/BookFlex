package com.ssafy.backend.service;

import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.exception.DuplicatedUsernameException;
import com.ssafy.backend.exception.LoginFailedException;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.mapper.UserMapper;
import com.ssafy.backend.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserDto userDto) {
        if (userMapper.findUserByUserEmail(userDto.getUserEmail()).isPresent()) {
            throw new DuplicatedUsernameException("이미 가입된 유저입니다");
        }

        userDto.setUserPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.save(userDto);

        return userMapper.findUserByUserEmail(userDto.getUsername()).get();
    }

    public String login(LoginDto loginDto) {
        UserDto userDto = userMapper.findUserByUserEmail(loginDto.getUserEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getUserPassword(), userDto.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다");
        }

        return jwtTokenProvider.createToken(userDto.getUserId(), Collections.singletonList(userDto.getUserRole()));
    }

    public UserDto findByUserId(Long userId) {
        return userMapper.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
    }
}
