package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Student;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;

//DAO 는 엔티티를 관리 - DTO 안씀
@RequiredArgsConstructor
public class StudentDao {
    private final DBConnectionMgr mgr;  //무조건 필요

    public void insert(Student student) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = mgr.getConnection();

            //student_id 첫번째 칼럼 - auto increment 이므로 default
            String sql = """
                    insert into student_tb
                    values (default, ?, ?, ?, ?, ?, ?, ?)
                    """;

            ps = con.prepareStatement(sql);
            // ? 개수 칼럼으로 채우기
            ps.setString(1, student.getStudentName());  //1번째 물음표
            ps.setString(2, student.getPhone());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getDepartmentId());
            ps.setInt(5, student.getGrade());
            ps.setString(6, student.getMajorType());
            ps.setString(7, student.getAdmissionYear());

            ps.execute();  // insert -> .execute()          //select -> .executeQuery(0, ResultSet 필요

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps);
        }
    }
}
