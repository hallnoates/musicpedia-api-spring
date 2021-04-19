package org.mozza.musicpediaapi.user.service;

import lombok.AllArgsConstructor;
import org.mozza.musicpediaapi.user.domain.LoginType;
import org.mozza.musicpediaapi.user.domain.User;
import org.mozza.musicpediaapi.user.dto.SignUpDto;
import org.mozza.musicpediaapi.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(LoginType loginType, SignUpDto signupDto) {
        Optional<User> byEmail = userRepository.findByEmail(signupDto.getEmail());
        if (byEmail.isEmpty()) {
            return null;
        }
        return null;
    }
}
