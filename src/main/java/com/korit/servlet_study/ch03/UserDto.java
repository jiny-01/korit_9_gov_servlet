package com.korit.servlet_study.ch03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String name;
    private String email;

    //DTO -> User 엔티티 객체
    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();

    }
}
