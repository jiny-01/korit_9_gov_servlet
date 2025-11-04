package com.korit.servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {
    private List<User> users;

    List<Map<String, String>> userList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();    //초기화 시 새 배열
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //#1)
        // username == "test"
        //요청
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        System.out.println("get 요청: " + req.getMethod());
        String userName = req.getParameter("username");
        String name = "test";
        System.out.println(userName);
//        System.out.println(users.get(userName));


        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                foundUser = user;
                break;
            }
        }

        //응답
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());  //한글 인코딩
        PrintWriter result = resp.getWriter();
        if (foundUser != null) {
            result.println("찾은 사용자: " + foundUser);
        } else {
            result.println("해당 username은 존재하지 않습니다: " + userName);
        }


        //#2) stream 이용
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser2 = foundUsers.isEmpty() ? null : foundUsers.get(0);
        if (Objects.isNull(foundUser)) {                                 //foundUser 못 찾은 경우
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username 은 존재하지 않습니다.");
            return;
        }
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());         //찾은 경우
        resp.getWriter().println(foundUser);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        req.setCharacterEncoding("UTF-8");  //요청 때 한글이 들어오므로 인코딩


        System.out.println("POST 요청 들어옴");

        //getParameter 로 값 가져오기
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        //#1) 빌더 패턴 이용
        User user = User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();

        //#2) 새로운 객체 생성
        users.add(new User(username, password, name, email));

        //응답
        resp.setStatus(HttpServletResponse.SC_OK);     //상태코드 200
        resp.setStatus(201);                //상태 : 201 Created (생성 성공)
        resp.setContentType("text/plain");  //타입: 텍스트
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());  //인코딩
        resp.getWriter().println("사용지 등록 완료");


        //최종
        System.out.println(users);

        //유효성 검사
//        private Map<String, String> validUser(User user)

    }
}
