package com.korit.servlet_study.ch05;

public class Filter2 implements Filter{
    @Override
    public void doFilter(Request req, Response resp, FilterChain filterChain) {
        System.out.println("필터2 전처리");
        System.out.println("필터3 호출함");
        filterChain.doFilter(req, resp);  //filter3 호출
        System.out.println("필터2 후처리");
    }
}
