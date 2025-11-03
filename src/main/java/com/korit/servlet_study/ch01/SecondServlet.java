package com.korit.servlet_study.ch01;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecondServlet extends HttpServlet {
    public SecondServlet() {
        System.out.println("SecondServlet 생성자 호출");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("SecondServlet 초기화");
        config.getServletContext().setAttribute("message", "난 SecondServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet Context 객체 가져와");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("서비스 요청 들어옴2");;
    }

    @Override
    public void destroy() {
        System.out.println("SecondServlet 소멸완료");
    }
}
