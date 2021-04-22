package org.mozza.musicpediaapi.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mozza.musicpediaapi.user.domain.LoginType;
import org.mozza.musicpediaapi.user.domain.User;
import org.mozza.musicpediaapi.user.dto.LoginDto;
import org.mozza.musicpediaapi.user.dto.SignUpDto;
import org.mozza.musicpediaapi.user.repository.UserRepository;
import org.mozza.musicpediaapi.user.service.UserService;
import org.mozza.musicpediaapi.user.validation.SignUpValidator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@RestController
@RequiredArgsConstructor
@Slf4j
@Validated // for @RequestParam @PathVariable validation
public class LoginController {

    private final SignUpValidator signUpValidator;
    private final UserRepository userRepository;
    private final UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(signUpValidator);
    }

    @GetMapping("/sign-up/check-email")
    public ResponseEntity checkEmail(@RequestParam @Email @NotBlank String email) {
        return ResponseEntity.ok().body(userRepository.existsByEmail(email));
    }

    @GetMapping("/sign-up/check-nickname")
    public ResponseEntity checkNickname(@RequestParam String nickname) {
        return ResponseEntity.ok().body(userRepository.existsByNickname(nickname));
    }

    @PostMapping("/sign-up/{loginType}")
    public ResponseEntity signup(@PathVariable LoginType loginType, @RequestBody @Valid SignUpDto signupDto) {
        signupDto.setLoginType(loginType);

        User user = userService.signUp(signupDto);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
