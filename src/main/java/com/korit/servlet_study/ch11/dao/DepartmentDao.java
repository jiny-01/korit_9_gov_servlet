package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Department;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DepartmentDao {
    private final DBConnectionMgr mgr;     //required argu, 필수 생성자 쓰려면 final

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();

        try {
            con = mgr.getConnection();

            String sql = """
                select
                    department_id,
                    department_code,
                    department_name
                from
                department_tb
                order by
                    department_id
                """;

            //PreparedStatement 객체 생성 - Driver 통해서 가능
            ps = con.prepareStatement(sql);
            //ResultSet
            rs = ps.executeQuery();

            while (rs.next()) {
                Department department = Department.builder()
                        .departmentId(rs.getInt("department_id"))        //컬럼 인덱스 / 컬럼명 둘 다 가능
                        .departmentCode(rs.getString("department_code"))
                        .departmentName(rs.getString("department_name"))
                        .build();
                departments.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps, rs);     //DB 연결을 끊겠다는 의미
        }

        return departments;
    }



}
