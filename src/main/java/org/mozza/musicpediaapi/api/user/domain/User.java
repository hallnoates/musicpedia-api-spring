package org.mozza.musicpediaapi.api.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String nickname;
    private LoginType loginType;
}
