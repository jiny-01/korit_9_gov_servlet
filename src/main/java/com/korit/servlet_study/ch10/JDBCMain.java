package com.korit.servlet_study.ch10;

import javax.xml.transform.Result;
import java.sql.*;

/*
* JDBC JavaDataBaseConnection
*
*
* */
public class JDBCMain {
    public static void main(String[] args) {
//         http://naver.com      -> http 프로토콜
//         jdbc:mysql://ip:port  -> jdbc:mysql 프로토콜
//         mysql 의 port: 기본 (3306), 우리가 설정(3309)

//        프로토콜://IP주소:PORT번호/데이터베이스(스키마)이름
        final String URL = "jdbc:mysql://localhost:3309/student_db";    //데이터베이스 URL
        final String USERNAME= "root";
        final String PASSWORD = "1q2w3e4r";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   //JDBC 4버전 이상부턴 생략가능
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = """
                    select * from student_tb where student_name = '김준일'
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();  //resultSet 이 나오려면 select 문 실행 시에만 가능 - 조회 시
            rs.next();                         //내장 메서드 - rs.hasNext()- Next 있는지?
            int studentId = rs.getInt("student_id");
            String studentName = rs.getString("student_name");
            System.out.println("id: " + studentId);
            System.out.println("name: " + studentName);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터베이스 연결에 실패했어요.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
