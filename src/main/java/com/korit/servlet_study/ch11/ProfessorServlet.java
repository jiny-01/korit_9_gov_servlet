package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.DepartmentDao;
import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.entity.Department;
import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.service.DepartmentService;
import com.korit.servlet_study.ch11.service.ProfessorService;
import com.korit.servlet_study.ch11.service.StudentService;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/professors")
public class ProfessorServlet extends HttpServlet {

    private ProfessorService professorService;    //NoArgs 가 초기화를 해줘야하는데 초기화를 안해줬으므로 final 달 수 없음
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        DBConnectionMgr dbConnectionMgr = DBConnectionMgr.getInstance();
        ProfessorDao professorDao = new ProfessorDao(dbConnectionMgr);
        professorService = new ProfessorService(professorDao);  //기능을 전달해주기 위함 new ProfessorDao(DBConnectionMgr.getInstance())
        objectMapper = new ObjectMapper();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // q 를 키값으로 하는 파람 뽑아내기
        String q = req.getParameter("q");
        //DB 랑 비교해서 찾아야하는데 DAO 로 바로 갈 수 없음 -> Service 필요
        //DAO 호출 - 이떄 찾는 값 q를 넘겨줌
        professorService.getProfessors(q);

        List<Professor> professorList = professorService.getProfessors();
        objectMapper.writeValue(resp.getWriter(), professorList);
    }
}
