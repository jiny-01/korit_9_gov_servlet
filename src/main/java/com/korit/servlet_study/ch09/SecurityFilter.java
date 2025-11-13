package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class SecurityFilter implements Filter {
    private final String SECRET = "1234";
    private ObjectMapper om = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //setStatus 같은 메서드 쓰기 위해서 다운캐스팅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //포스트맨의 Header 부분에 정의한 "sercret":123
        String secret = request.getHeader("Secret"); // secret : 123 들어감
        System.out.println(secret); //-> 123

        //SECRET 키가 일치하지 않을 떄 - 바로 응답주고 return 에서 끝나서 서블릿까지 못 감
        if (!SECRET.equals(secret)) {
            response.setStatus(401);
            om.writeValue(response.getWriter(), Map.of("message", "요청 권한이 없습니다."));
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
