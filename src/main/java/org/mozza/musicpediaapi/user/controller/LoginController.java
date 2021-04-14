package org.mozza.musicpediaapi.user.controller;

import org.mozza.musicpediaapi.user.dto.LoginDto;
import org.mozza.musicpediaapi.user.dto.SignupDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class LoginController {

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid SignupDto signupDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
