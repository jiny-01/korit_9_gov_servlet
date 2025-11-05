package com.korit.servlet_study.ch07;

import com.korit.servlet_study.ch05.Request;
import com.korit.servlet_study.ch05.Response;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("인코딩 필터 실행");
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }
}
