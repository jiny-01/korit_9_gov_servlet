package com.korit.servlet_study.ch08;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private String title;
    private String content;
    private String writer;

}

//@Builder 안에 @AllArgu 포함되어있음 => Builder 쓸건데 NoArgu 도 필요하다면
//@NoArgu 달아줘야함 -  @NoArgu 가 우선순위가 높음

//@builder 안 쓸거면 @Data

