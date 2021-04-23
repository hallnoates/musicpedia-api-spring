package org.mozza.musicpediaapi.api.user.controller;

import lombok.RequiredArgsConstructor;
import org.mozza.musicpediaapi.api.user.domain.User;
import org.mozza.musicpediaapi.api.user.repository.UserRepository;
import org.mozza.musicpediaapi.api.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUsers() {
        User user = User.builder()
                .id(10)
                .name("yumi")
                .build();
        return ResponseEntity.ok(user);
    }
    // GET /users/10
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(user);
    }


}
