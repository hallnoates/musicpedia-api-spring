package org.mozza.musicpediaapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mozza.musicpediaapi.user.domain.LoginType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nickname;

    private String password;
    private String name;
    private LoginType loginType;
}
