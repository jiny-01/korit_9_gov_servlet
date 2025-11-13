package com.korit.servlet_study.ch01;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//추상클래스를 상속했는데 에러 없음 -> 추상메서드가 하나도 없을 경우
public class FirstServlet extends HttpServlet {


    public FirstServlet() {
        System.out.println("FirstServlet 생성자 호출");
    }
    //서블릿 객체 생성 시 초기 세팅할 게 있을 때 사용
    //역할: 서블릿 객체 생성 시 초기 세팅할 게 있을 때 사용

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("서비스 메서드 요청 들어옴");
    }
    //브라우저에서 /FirstServlet 요청이 들어올 때마다 호출

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("요청 들어옴");
    }
    //브라우저 주소창에서 /FirstServlet으로 접근할 때.

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("초기화");
        config.getServletContext().setAttribute("age", 25); //config.getServletContext()는 모든 서블릿이 공유하는 영역
    }
    //서블릿이 처음 초기화될 때 한 번만 호출
    //ServletConfig - 이 서블릿에만 해당되는 설정
    //ServletContext - 모든 서블릿이 공유하는 공간, 서버가 실행되고 소멸되기 전까지 계속 존재
    //config → 이 서블릿 개별 초기 설정 정보(ServletConfig)
    //config.getServletContext() → 웹 애플리케이션 전체에서 공유되는 공간(ServletContext)
    //따라서 age = 25라는 데이터를 모든 서블릿이 공유 가능하게 저장

    @Override
    public void destroy() {
        System.out.println("소멸");
    }
    //톰캣이 종료되거나, 해당 서블릿이 언로드될 때 한 번 호출
}
