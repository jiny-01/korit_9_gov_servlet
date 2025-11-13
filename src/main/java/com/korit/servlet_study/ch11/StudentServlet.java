package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.service.StudentService;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService(new StudentDao(DBConnectionMgr.getInstance()));
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDto studentDto = objectMapper.readValue(req.getReader(), StudentDto.class);
        //body에서 json 으로 보낸 dto -> objectmapper 가 studentDto 객체로 바꿔줌

        System.out.println(studentDto);
        studentService.save(studentDto);  //service 에 dto 전달해서 insert 되도록함
    }
}
