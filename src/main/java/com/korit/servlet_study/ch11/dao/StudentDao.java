package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Student;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.*;

//DAO 는 엔티티를 관리 - DTO 안씀
@RequiredArgsConstructor
public class StudentDao {
    private final DBConnectionMgr mgr;  //무조건 필요 - requiredArgu 시 생성된 것
//    private final DBConnectionMgr mgr2;
//    private int a;

    //기본인 NoArgs 여도 상수 초기화만 해주면 사용가능
//    public StudentDao() {
//        mgr = DBConnectionMgr.getInstance();
////        mgr2 = DBConnectionMgr.getInstance();
//    }


    public void insert(Student student) {
        //try 문의 지역변수이므로 전역 사용하기 위해 초기화해줌 - finally 에서 쓰기위함
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = mgr.getConnection();

            //student_id 첫번째 칼럼 - auto increment 이므로 default
            String sql = """
                    insert into student_tb
                    values (default, ?, ?, ?, ?, ?, ?, ?)
                    """;

            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Statement.RETURN_GENERATED_KEYS 세팅되어있어야 getgeneratedKeys 사용 가능
            // ? 개수 칼럼으로 채우기
            ps.setString(1, student.getStudentName());  //1번째 물음표
            ps.setString(2, student.getPhone());
            ps.setString(3, student.getEmail());
            ps.setInt(4, student.getDepartmentId());
            ps.setInt(5, student.getGrade());
            ps.setString(6, student.getMajorType());
            ps.setString(7, student.getAdmissionYear());

//            ps.execute();  // insert -> .execute()          //select -> .executeQuery(0, ResultSet 필요

            if (!ps.execute()) {                     //true -> insert 정상수행 / false -> insert 실패
                throw new SQLException();
            }

            //핵심로직 -> try 안에서 정의
            //키 값 조회 - resultset 으로 가져옴
            rs = ps.getGeneratedKeys();   //execute 즉, insert 할 때 키값을 여러 개 넣어주기 때문에 keys
            while(rs.next()) {
                int newStudentId = rs.getInt(1);  //1번째 컬럼
                student.setStudentId(newStudentId);          //새로 가져온 id 를 student 객체의 id로 넣어주기

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mgr.freeConnection(con, ps, rs);  //freeConnection 안에 이미 소멸시키는 단계가 있음
        }
    }
}
