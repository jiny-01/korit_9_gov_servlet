package com.korit.servlet_study.ch11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch11.dao.DepartmentDao;
import com.korit.servlet_study.ch11.entity.Department;
import com.korit.servlet_study.ch11.service.DepartmentService;
import com.korit.servlet_study.ch11.util.DBConnectionMgr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;
    private ObjectMapper objectMapper;
    //final 필드는 반드시 생성자에서 초기화되어야 하기 때문에 final 빼줘야함

    public DepartmentServlet() {
//        DBConnectionMgr dbConnectionMgr = DBConnectionMgr.getInstance();
//        DepartmentDao departmentDao = new DepartmentDao(dbConnectionMgr);
//        departmentService = new DepartmentService(departmentDao);
    }

    //생성이 되고 나서 init 되기 때문에 일단 오류뜸
    @Override
    public void init() throws ServletException {
        DBConnectionMgr dbConnectionMgr = DBConnectionMgr.getInstance();   //싱글톤으로 가져옴
        DepartmentDao departmentDao = new DepartmentDao(dbConnectionMgr);  //
        departmentService = new DepartmentService(departmentDao);
        objectMapper = new ObjectMapper();   //objectmapper 생성 안해주면 오류
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentService.getDepartments();
        objectMapper.writeValue(resp.getWriter(), departments);

    }
}
