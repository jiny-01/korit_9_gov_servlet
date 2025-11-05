package com.korit.servlet_study.ch05;

public class Filter3 implements Filter{
    @Override
    public void doFilter(Request req, Response resp, FilterChain filterChain) {
        System.out.println("필터3 전처리");
        System.out.println("서블릿 호출함");
        filterChain.doFilter(req, resp);  //filter2 호출
        System.out.println("필터3 후처리");

    }
}
