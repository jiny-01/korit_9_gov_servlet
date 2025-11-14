package com.korit.servlet_study.ch11.dao;

import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProfessorDao {
//    private final DBConnectionMgr mgr;

//    Connection con = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;  //select 니까 가능

    //findAll - 다건조회
    //호출이 되어지면 매번 빈 박스가 생김 - insert 해서 채워넣고 리턴해줌

    //#1) findAll 사용
//    public List<Professor> findAll() {
//        List<Professor> professorList = new ArrayList<>();
//
//        Connection con = DBConnectionMgr.getInstance().getConnection();  //싱글톤이니까 바로 가져올 수 있음
//
//        try {
//            con = mgr.getConnection();
//
//            String query = """
//                    select
//                        *
//                    from professor_tb
//                    """;
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next()) {
//                Professor professor = Professor.builder()
//                        .professorId(rs.getInt("professor_id"))
//                        .professorName(rs.getString("professor_name"))
//                        .build();
//                professorList.add(professor);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("데이터베이스 연결 중 오류 발생");
//        } finally {
//            mgr.freeConnection(con, ps, rs);
//        }
//
//
//        return professorList;
//    }

    //#2) findAllByName 방식 이용
    //* find - 단건조회  -> Entity 로 바로 생성 //  findAll - 다건조회 -> List 에 담아야함
    public List<Professor> findAllLikeName(String name) {
        DBConnectionMgr mgr = DBConnectionMgr.getInstance();
        List<Professor> professors = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            con = DBConnectionMgr.getInstance().getConnection();  //길어지니까 DBConnectionMgr.getInstance() 을 위에 변수로 선언
            //1. Connect -> DAO - DB 연결 완료
            con = mgr.getConnection();

            //2. SQL 쿼리 작성
            String sql = """
                    select 
                        professor_id,
                        professor_name
                    from
                        professor_tb
                    where
                        professor_name like concat('%', ?, '%')
                    """;

            //setString()은 ps 객체의 메서드이기 때문에 ps가 먼저 생성되어야함
            //쿼리문 담음
            ps = con.prepareStatement(sql);
            ps.setString(1, name);

            ps.executeQuery();  //리턴 -> resultset

            //rs의 역할 - DB에서 행 이동, 칼럼명을 키로 값 가져오기
            rs = ps.executeQuery();

            //한 행을 읽어서 하나의 Professor 객체를 생성 -> 리스트에 담아줌
            while (rs.next()) {
                Professor newProcessor = Professor.builder()
                        .professorId(rs.getInt("professor_id"))
                        .professorName(rs.getString("professor_name"))
                        .build();
                professors.add(newProcessor);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
//            rs.close();
//            ps.close();
            mgr.freeConnection(con, ps, rs);        //.freeConnection 에서 Null 체크 해줌
//            DBConnectionMgr.getInstance().freeConnection(con);
        }
        return professors;
    }
}
