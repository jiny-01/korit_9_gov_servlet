package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.CourseDao;
import com.korit.servlet_study.ch11.dto.CourseDto;
import com.korit.servlet_study.ch11.entity.Course;
import com.korit.servlet_study.ch11.service.CourseService;
import com.korit.servlet_study.ch11.service.StudentService;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private CourseService courseService;
    private ObjectMapper objectMapper;

    //매 요청마다 새로 만들지 않고 한번 생성후 계속 사용 가능 - Servlet 처음 init 시 생성 1번만
    @Override
    public void init() throws ServletException {
        //DBConnectionMgr.getInstance() - 싱글톤 객체(DB커넥션 풀 관리자)
        //DAO (Data Access Object) - SQL 실행 전담
        //CourseService - 비즈니스로직계층
        //- Servlet(=Controller) 와 DAO 사이 중간다리
        courseService = new CourseService(new CourseDao(DBConnectionMgr.getInstance()));
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDto courseDto = objectMapper.readValue(req.getReader(), CourseDto.class);

        System.out.println("추가할 과목: " + courseDto);
//        courseService.save(courseDto);
        Course newCourse = courseService.save(courseDto);

        objectMapper.writeValue(resp.getWriter(), newCourse);

    }
}
