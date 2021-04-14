package org.mozza.musicpediaapi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
