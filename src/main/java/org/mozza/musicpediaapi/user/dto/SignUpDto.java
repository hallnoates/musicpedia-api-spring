package org.mozza.musicpediaapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private String name;
}
