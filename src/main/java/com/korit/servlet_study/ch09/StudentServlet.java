package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//서블릿의 생명주기
//프로젝트 시작 -> 생성: init() -> 서비스: doGet, doPost -> 소멸: destory()
@WebServlet("/study/students")
public class StudentServlet extends HttpServlet {
//    private List<Student> studentList;
    private StudentRepository studentRepository;
    private ObjectMapper om = new ObjectMapper();
    private int autoId = 20250001;  //초기값 설정, 추가할 때마다 ++ 되도록

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = new StudentRepository();
        //servlet context 에다 studentRepository 주소를 넣어둠
        // 다른 서블릿이나 외부에서 공통으로 꺼내쓸 수 있는 영역 -> ServletContext
        config.getServletContext().setAttribute("sr", studentRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //요청형식
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        //응답형식
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");
        //=> 필터로 정의함 - EncodingFilter

        String searchNameValue = req.getParameter("searchName");  //검색 키워드  "searchName" : 파라미터으ㅣ 키 값
//        if (Objects.isNull(searchNameValue)) {              //키워드 검색결과 없으면
//            om.writeValue(resp.getWriter(), studentList);
//            return;
//        }
//        List<Student> foundStudents = studentList.stream()
//                .filter(student -> student.getName().contains(searchNameValue))
//                .toList();

//        om.writeValue(resp.getWriter(), foundStudents);
//        StudentRepository studentRepository = new StudentRepository(); //-전역에서 생성해야함
        om.writeValue(resp.getWriter(), studentRepository.findAllBySearchNameValue(searchNameValue));

        //students 배열 전체 조회 -> Json 배열로 응답
//        om.writeValue(resp.getWriter(), studentList);
//        om.writeValue(resp.getWriter(), foundStudents);
//        System.out.println("리스트 확인: " + studentList);
//        System.out.println("찾은 학생: " + foundStudents);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //요청형식
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());  //("UTF-8") 도 가능
//        //응답
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");
        //=> 필터로 정의함 - EncodingFilter

//        BufferedReader bf = req.getReader();
//        om.readValue(bf, Student.class);  //json -> Student 객체

        // ObjectMapper 이용 : JSON → Student 객체
        Student student = om.readValue(req.getReader(), Student.class);
        studentRepository.insert(student);
        student.setId(autoId++);   //최신의 것으로
        //이때 Student 객체를 생성하는데 기본적으로 NoArgu 조건으로 생성함
        System.out.println("추가한 학생: " + student);
//        studentList.add(student);  //리스트에 추가
//        System.out.println(studentList);



        //map -> JSNO 으로 변환해줌
        om.writeValue(resp.getWriter(), Map.of("message", "학생 추가 완료"));

        //응답객체
        Response response = Response.builder()
                .message("학생 등록 완료")
                .build();

//        om.writeValue(resp.getWriter(), response);




    }
}
