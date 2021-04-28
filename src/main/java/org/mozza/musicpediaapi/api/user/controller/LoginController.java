package org.mozza.musicpediaapi.api.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mozza.musicpediaapi.api.user.domain.User;
import org.mozza.musicpediaapi.api.user.dto.LoginDto;
import org.mozza.musicpediaapi.api.user.dto.SignUpDto;
import org.mozza.musicpediaapi.api.user.dto.TokenDto;
import org.mozza.musicpediaapi.api.user.repository.UserRepository;
import org.mozza.musicpediaapi.api.user.service.UserService;
import org.mozza.musicpediaapi.api.user.validation.SignUpValidator;
import org.mozza.musicpediaapi.application.jwt.JwtFilter;
import org.mozza.musicpediaapi.common.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sign-up/check-email")
    public ResponseEntity<ResponseObject> checkEmail(@RequestParam @Email @NotBlank String email) {

        return ResponseEntity.ok().body(ResponseObject.of(200,userRepository.existsByEmail(email)));
    }

    @GetMapping("/sign-up/check-nickname")
    public ResponseEntity<ResponseObject> checkNickname(@RequestParam String nickname) {
        return ResponseEntity.ok().body(ResponseObject.of(200,userRepository.existsByNickname(nickname)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseObject> signup(@RequestBody @Valid SignUpDto signupDto, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.badRequest().body(ResponseObject.fail(errors));
        }

        signUpValidator.validate(signupDto, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseObject.fail(errors));
        }

        User user = userService.signUp(signupDto);
        return ResponseEntity.ok().body(ResponseObject.of(200,user));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody @Valid LoginDto loginDto) {
        TokenDto tokenDto = userService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getToken());

        return ResponseEntity.ok().headers(httpHeaders).body(ResponseObject.ok(tokenDto));
    }

}
