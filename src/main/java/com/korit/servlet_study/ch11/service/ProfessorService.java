package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.ProfessorDao;
import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Department;
import com.korit.servlet_study.ch11.entity.Professor;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProfessorService {
//    private final ProfessorDao professorDao;

    //서비스에 키 값 매개변수로 전달
    public List<Professor> getProfessors(String query) {
        //DAO에서 호출하기 위해 DAO 생성해서 사용
        ProfessorDao professorDao = new ProfessorDao();
        return professorDao.findAllLikeName(query);
    }
}
