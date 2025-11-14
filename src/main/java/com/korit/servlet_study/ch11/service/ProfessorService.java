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
    private final ProfessorDao professorDao;

    public List<Professor> getProfessors() {
        return professorDao.findAllLikeName("");
    }
}
