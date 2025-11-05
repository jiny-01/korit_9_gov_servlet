package com.korit.servlet_study.ch04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/ch04/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(servletRequest, servletResponse);
        //dofilter 호출해줘야 다음 필터로 넘어감
        //다음 필터 없을 때 - servlet 의 doget, dopost 호출되는 것
//        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        filterChain.doFilter(servletRequest, servletResponse);
//        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //printwriter 하기 전에 데이터가 인코딩 되어있어야함
        //그래서 이렇게 하는 게 아님
        //이건 doFilter 에서 담고나서 인코딩해주는 것과 같음

        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
