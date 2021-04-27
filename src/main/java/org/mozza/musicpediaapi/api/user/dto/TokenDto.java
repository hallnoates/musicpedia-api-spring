package org.mozza.musicpediaapi.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 사용자에게 반환할 JWT token
public class TokenDto {

    private String token;
}
