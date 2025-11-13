package com.korit.servlet_study.ch09;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@WebFilter("/study/students")
public class EncodingFilter implements Filter {

    //이 필터 지나서 처리된 후 서블릿에 객체 전달됨
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("인코딩 필터 실행");
        String encodingType = StandardCharsets.UTF_8.name();
        servletRequest.setCharacterEncoding(encodingType);
        servletResponse.setCharacterEncoding(encodingType);
        servletResponse.setContentType("application/json");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
