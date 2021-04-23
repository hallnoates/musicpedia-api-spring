package org.mozza.musicpediaapi.api.user.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mozza.musicpediaapi.api.user.domain.LoginType;
import org.mozza.musicpediaapi.api.user.dto.SignUpDto;
import org.mozza.musicpediaapi.api.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
@Slf4j
public class SignUpValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpDto.class); // SignUpDto.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SignUpDto signUpDto = (SignUpDto)object;

        if (signUpDto.getLoginType() == LoginType.NORMAL) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "NotBlank","password can not be blank");
        }
        /*
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            errors.rejectValue("email", "invalid", new Object[]{signUpDto.getEmail()},"이미 사용중인 이메일입니다");
        }

        if (userRepository.existsByNickname(signUpDto.getNickname())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpDto.getName()},"이미 사용중인 닉네임입니다");
        }
        */
    }
}
