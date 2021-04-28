package org.mozza.musicpediaapi.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mozza.musicpediaapi.api.user.domain.LoginType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class LoginDto {

    @Email
    @NotBlank
    private String email;
    private String password;
    private LoginType loginType;
}
