package com.korit.servlet_study.ch10;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDBCMain2 {
    public static void main(String[] args) {
        //DB 연결 정보 설정
        final String URL = "jdbc:mysql://localhost:3309/student_db";    //데이터베이스 URL
        final String USERNAME= "root";
        final String PASSWORD = "1q2w3e4r";

//        String searchData = "프로그래밍언어론";
        String searchData = "";


        //프로그래밍언어론 에 대한 데이터 다 들고오기
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //1. DB Connection - DB 연결 객체 설정
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //2. SQL 작성
//            String sql = """
//                    select * from course_tb ct
//                        join professor_tb pt on pt.professor_id = ct.professor_id
//                        where
//                            ct.course_name = '프로그래밍언어론';
//                    """;

            String sql2 = """
                    select 
                        ct.course_id,
                        ct.course_code,
                        ct.course_name,
                        pt.professor_id,
                        pt.professor_name,
                        ct.credit,
                        ct.enrollment_capacity,
                        ct.classroom
                    from course_tb ct
                        join professor_tb pt on ct.professor_id = pt.professor_id
                        where
                        ct.course_name like concat('%', ?, '%')       
                    """;
            //ct.course_name like '%프로그래밍%' 형태가 되어야함
            // -> like concat('%', ?, '%')

            //3, SQL 문 실행을 위한 PreparedStatement 생성
            // SQL 을 DB 에 보낼 준비를 하는 객체
            PreparedStatement status = connection.prepareStatement(sql2);
            //4. '?' 와일드카드 위치에 값 맵핑(1 = 몇 번째 물음표인지)  -> ? : PreparedStatement 의 문법
            status.setString(1, searchData);
            //5. 결과를 ResultSet 객체로 가져오기
            ResultSet data = status.executeQuery();
            while (data.next()) {                                    //rs.next() - 커서 역할 => 다음 행으로 이동
                Map<String, Object> resultMap = Map.of(
                        "course_id", data.getInt("course_id"),       //DB 칼럼 이름으로 데이터 꺼내기
                        "course_code", data.getString("course_code"),
                        "course_name", data.getString("course_name"),
                        "professor_name", data.getString("professor_name"),
                        "credit", data.getInt("credit"),
                        "enrollment_capacity", data.getInt("enrollment_capacity"),
                        "classroom", data.getString("classroom")
                );
                //2) 순서 보장가능한 linked Map 사용
                Map<String, Object> linkedMap = new LinkedHashMap<>();
                resultMap.put("course_id", data.getInt("course_id"));
                resultMap.put("course_code", data.getString("course_code"));
                resultMap.put("course_name", data.getString("course_name"));
                resultMap.put("professor_name", data.getString("professor_name"));
                resultMap.put("credit", data.getInt("credit"));
                resultMap.put("enrollment_capacity", data.getInt("enrollment_capacity"));
                resultMap.put("classroom", data.getString("classroom"));
                System.out.println("결과맵: " + resultMap);

                //객체 생성해서 안에 넣기
                // 이 클래스들은 이 지역안에서만 쓰고 버리는 일회용 클래스
                @Data
                @AllArgsConstructor
                class Professor {
                    private int professorId;
                    private String professorName;
                }

                @Data
                @AllArgsConstructor
                class Course {
                    private int courseId;
                    private String courseCode;
                    private String courseName;
                    private Professor professor;
                    private int credit;
                    private int enrollmentCapacity;
                    private String classroom;
                }

                Course course = new Course(
                        data.getInt("course_id"),
                        data.getString("course_code"),
                        data.getString("course_name"),
                        new Professor(data.getInt("professor_id"), data.getString("professor_name")),
                        data.getInt("credit"),
                        data.getInt("enrollment_capacity"),
                        data.getString("classroom")
                );
                System.out.println(course);
            }

            // 칼럼 매칭해서 get 으로 값 가져오기
//            int courseId = data.getInt("course_id");
//            String courseCode = data.getString("course_code");
//            String courseName = data.getString("course_name");
//            String professorId = data.getString("professor_id");
//            int credit = data.getInt("credit");
//            int enrollmentCapacity = data.getInt("enrollment_capacity");
//            String classroom = data.getString("classroom");
//            String professorName = data.getString("professor_name");
//
//            System.out.println("과목ID: " + courseId);
//            System.out.println("과목코드: " + courseCode);
//            System.out.println("과목명: " + courseName);
//            System.out.println("교수ID: " + professorId);
//            System.out.println("학점: " + credit);
//            System.out.println("수강인원: " + enrollmentCapacity);
//            System.out.println("강의실: " + classroom);
//            System.out.println("교수이름: " + professorName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 연결 실패");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
















































