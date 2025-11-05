package com.korit.servlet_study.ch04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/ch04/*")
public class FirstFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 (전처리)");
        //dofilter 호출
        filterChain.doFilter(servletRequest, servletResponse);  //전처리 필터에 매개변수 그대로 전달
        System.out.println("필터 (후처리)");


    }
}
