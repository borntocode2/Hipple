package com.example.hiddenplace.service;

import com.example.hiddenplace.domain.User;
import com.example.hiddenplace.dto.RequestDto;
import com.example.hiddenplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //@RequiredArgsConstructor는 초기화 되지않은 final 필드의 생성자를 생성해 줍니다.
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void JoinProcess(RequestDto requestDto) {
        Boolean isExist = userRepository.existsByUsername(requestDto.getUsername());
        if (isExist) {
            return; // false를 반환해서 Controller에서 회원가입이 안됨 이라는 메시지도 출력가능
        }

        User user = User.builder()
                .username(requestDto.getUsername())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword())) //DB에 비번이 암호화되어 저장됨
                .role("ROLE_USER")
                .build();

        userRepository.save(user);


    }
}
