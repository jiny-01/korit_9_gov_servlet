package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private int status;
    private String characterEncoding;
    private String contentType;
    private String data;
}
