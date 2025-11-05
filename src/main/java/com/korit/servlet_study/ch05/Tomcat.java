package com.korit.servlet_study.ch05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tomcat {
    public static void main(String[] args) {
        System.out.println("요청");        //요청 들어오면
        //요청 정보를 담을 request, response 객체 생성
        Request request = new Request();
        Response response = new Response();

        //요청할 정보 세팅 => servlet A 의 GET 메서드 호출
        request.setUrl("/servlet/a");  //servletA 로 이동
        request.setMethod("GET");
        request.setData("요청 테스트 데이터");

//        //servlet B 의 POST 메서드 호출
//        request.setUrl("/servlet/b");  //servletB 로 이동
//        request.setMethod("POST");
//        request.setData("요청 테스트 데이터");

        //servlet 관리하는 리스트
        //url 경로와 서블릿 객체 매핑
        Map<String, Servlet> servletMap = Map.of(
                "/servlet/a", new ServletA(),   //키: 서블릿 url , 밸류: 서블릿 A 객체
                    "/servlet/b", new ServletB()
        );

        List<Filter> filters = List.of(
                new Filter1(),
                new Filter2(),
                new Filter3()
        );

        //FilterChain 객체 생성
        //filters - filter 1,2,3 담은 리스트
        //servletMap.get(request.getUrl()) - request.getURL 을 키로 하는 밸류 ,
        // 즉 서블릿 객체가 선택됨,
        // 0 : 현재필터번호
        //이때 doFilter 메서드로 필터1,2,3 전처리 > 서블릿 실행 > 후처리 실행
        FilterChain filterChain = new FilterChain(filters, servletMap.get(request.getUrl()), 0);
        filterChain.doFilter(request, response);  //필터체인에 doFilter 호출 (request: 데이터 O, response: 빈 깡통)


        //FilterChain 이 모두 실행된 후 request 의 메서드 확인 - 지금은 GET
        //실제는 서블릿 A 내에서 doGet, doPost 메서드 실행됨
//        switch (request.getMethod()) {
//            case "GET":
//                servletMap.get(request.getUrl()).doGet(request, response);
//                //servletA 가져와서 doGet 메서드 호출
//                break;
//            case "POST":
//                servletMap.get(request.getUrl()).doPost(request, response);
//                break;
//        }
        System.out.println(response);
        System.out.println("응답");
    }
}
