package com.korit.servlet_study.ch05;

import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/b")
public class ServletB extends Servlet{

    @Override
    public void doGet(Request req, Response resp) {
        System.out.println("서블릿B 에서 GET 호출");
    }

    @Override
    public void doPost(Request req, Response resp) {
        System.out.println("서블릿B 에서 POST 호출");
    }
}
