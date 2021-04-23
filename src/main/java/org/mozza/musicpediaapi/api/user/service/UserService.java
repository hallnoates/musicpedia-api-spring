package org.mozza.musicpediaapi.api.user.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.mozza.musicpediaapi.api.user.domain.User;
import org.mozza.musicpediaapi.api.user.dto.SignUpDto;
import org.mozza.musicpediaapi.api.user.repository.UserRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User signUp(SignUpDto signupDto) {
        if (!userRepository.findByEmail(signupDto.getEmail()).isEmpty()) {
            throw new DuplicateKeyException("사용중인 이메일 입니다.");
        }
        return userRepository.save(modelMapper.map(signupDto, User.class));
    }
}
