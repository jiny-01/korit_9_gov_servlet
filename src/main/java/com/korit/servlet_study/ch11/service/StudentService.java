package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    public void save(StudentDto studentDto) {
        Student student = studentDto.toEntity();    //Dto 에 정의한 toEntity 이용해서 Dto -> Entity 변환
        studentDao.insert(student);                 //Dao 에 insert 엔티티
    }
}
