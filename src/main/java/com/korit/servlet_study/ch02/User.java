package com.korit.servlet_study.ch02;

import lombok.*;

//lombok -> 리플렉션을 적용한 것
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
public class User {
    private String username;
    private String password;
    private String name;
    private String email;


}
