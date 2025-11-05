package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FilterChain {
    private List<Filter> filters;
    private Servlet servlet;
    private int currentOrder;
    public void doFilter(Request req, Response resp) {
        if (currentOrder < filters.size()) {          //filters.size = 3 (필터 1~3)
            filters.get(currentOrder++).doFilter(req, resp, this);  //0번 인덱스 - 1번째 필터 객체에서 dofilter 호출
            //currentOrder : 들어갈 땐 -인데 doFilter 실행되면 1로 될 것
            //filter1 -> 1 / filter2 -> 2 / filter3 -> 3
            //filter 3 다음 필터 없음, currentOrder = 3 < 사이즈 false
            //루프 탈출해서 어떤 메서드인지 확인해서 servlet 에서 doGet, doPost 호출
            return;
        }
            if ("GET".equalsIgnoreCase(req.getMethod())) {    //getMethod = -> GET 일 때
                servlet.doGet(req, resp);                     //servlet 의 doGet 호출
            } else if ("POST".equalsIgnoreCase(req.getMethod())) {
                servlet.doPost(req, resp);
            }



    };
}
