package org.mozza.musicpediaapi.api.user.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.mozza.musicpediaapi.api.user.domain.Role;
import org.mozza.musicpediaapi.api.user.domain.User;
import org.mozza.musicpediaapi.api.user.dto.LoginDto;
import org.mozza.musicpediaapi.api.user.dto.SignUpDto;
import org.mozza.musicpediaapi.api.user.dto.TokenDto;
import org.mozza.musicpediaapi.api.user.repository.UserRepository;
import org.mozza.musicpediaapi.application.jwt.JwtFilter;
import org.mozza.musicpediaapi.application.jwt.TokenProvider;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    public User signUp(SignUpDto signupDto) {
        if (!userRepository.findByEmail(signupDto.getEmail()).isEmpty()) {
            throw new DuplicateKeyException("사용중인 이메일 입니다.");
        }

        User user = modelMapper.map(signupDto, User.class);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public TokenDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        return new TokenDto(jwt);
    }
}
