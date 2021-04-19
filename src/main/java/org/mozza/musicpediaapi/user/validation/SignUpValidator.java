package org.mozza.musicpediaapi.user.validation;

import lombok.RequiredArgsConstructor;
import org.mozza.musicpediaapi.user.dto.SignUpDto;
import org.mozza.musicpediaapi.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpDto.class); // SignUpDto.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpDto signUpDto = (SignUpDto)object;
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpDto.getEmail()},"이미 사용중인 이메일입니다");
        }

        if (userRepository.existsByNickname(signUpDto.getNickname())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpDto.getName()},"이미 사용중인 닉네임입니다");
        }
    }
}
