package com.korit.servlet_study.ch01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletConfigTest extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object age = req.getServletContext().getAttribute("age");
        System.out.println(age);
    }
    //req.getServletContext() → 이미 톰캣이 만들어 놓은 웹 애플리케이션 전역 공간 참조
    //FirstServlet에서 저장한 age = 25를 읽음
    //모든 서블릿이 공유하는 데이터이므로, 여기서도 접근 가능
    //매 요청마다 호출됨
}
