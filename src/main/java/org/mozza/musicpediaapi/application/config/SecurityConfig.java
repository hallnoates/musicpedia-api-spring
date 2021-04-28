package org.mozza.musicpediaapi.application.config;

import lombok.RequiredArgsConstructor;
import org.mozza.musicpediaapi.api.user.domain.Role;
import org.mozza.musicpediaapi.application.jwt.JwtAccessDeniedHandler;
import org.mozza.musicpediaapi.application.jwt.JwtAuthenticationEntryPoint;
import org.mozza.musicpediaapi.application.jwt.JwtSecurityConfig;
import org.mozza.musicpediaapi.application.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // security에서 기본적으로 생성하는 login 페이지 비활성화
                .httpBasic().disable()

                // rest API
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // session 사용 X
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // HttpServletRequest를 사용하는 요청들에 대한 접근권한 설정
                .and()
                .authorizeRequests()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/users/**").hasAuthority(String.valueOf(Role.ADMIN))
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}