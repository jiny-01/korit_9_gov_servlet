package com.korit.servlet_study.ch10;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/ch10/test/servlet")
public class TestServlet extends HttpServlet {
    private final String URL = "jdbc:mysql://localhost:3309/student_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "1q2w3e4r";

    private ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //요청
       String searchCourseName = req.getParameter("course_name");

       //응답 형식지정
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        //데이터베이스 연결
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection dbConnect = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = """
                    select 
                        *
                    from
                        course_tb ct 
                        join professor_tb pt on pt.professor_id = ct.professor_id
                    where ct.course_name LIKE CONCAT('%', ?, '%')
                    """;
            PreparedStatement status = dbConnect.prepareStatement(query);
            status.setString(1, searchCourseName);

            ResultSet resultData = status.executeQuery();

            // 여러 개의 결과를 담기 위한 리스트
            List<Map<String, Object>> resultList = new ArrayList<>();
            while(resultData.next()) {
                Map<String, Object> resultMap = Map.of(
                        "course_id", resultData.getInt("course_id"),       //DB 칼럼 이름으로 데이터 꺼내기
                        "course_code", resultData.getString("course_code"),
                        "course_name", resultData.getString("course_name"),
                        "professor_name", resultData.getString("professor_name"),
                        "credit", resultData.getInt("credit"),
                        "enrollment_capacity", resultData.getInt("enrollment_capacity"),
                        "classroom", resultData.getString("classroom")
                );
                resultList.add(resultMap);
            }
            // ObjectMapper가 직접 JSON 변환해서 응답으로 써줌
            om.writeValue(resp.getWriter(), resultList);
            System.out.println("검색 결과 확인: " + resultList);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 연결 실패함!!!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
