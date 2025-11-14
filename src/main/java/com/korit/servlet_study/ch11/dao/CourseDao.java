package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CourseDao {
    private final DBConnectionMgr mgr;

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insert(Course course) {

        try {
            con = mgr.getConnection();

            String sql = """
                    insert into course_tb
                    values (default, ?, ?, ?, ?, ?, ?)
                    """;

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getProfessorId());
            ps.setInt(4, course.getCredit());
            ps.setInt(5, course.getEnrollmentCapacity());
            ps.setString(6, course.getClassroom());


            //insert 수행결과
            if (ps.executeUpdate() < 1) {                     //true -> insert 정상수행 / false -> insert 실패
                throw new SQLException();
            }

            //핵심로직 -> try 안에서 정의
            //키 값 조회 - resultset 으로 가져옴
            rs = ps.getGeneratedKeys();   //execute 즉, insert 할 때 키값을 여러 개 넣어주기 때문에 keys
            while(rs.next()) {
                int newCourseId = rs.getInt(1);  //1번째 컬럼
                course.setCourseId(newCourseId);          //새로 가져온 id 를 course 객체의 id로 넣어주기

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps, rs);
        }
    }
}
