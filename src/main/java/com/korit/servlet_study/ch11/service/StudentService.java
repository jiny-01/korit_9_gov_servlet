package com.korit.servlet_study.ch11.service;

import com.korit.servlet_study.ch11.dao.StudentDao;
import com.korit.servlet_study.ch11.dto.StudentDto;
import com.korit.servlet_study.ch11.entity.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {
    private final StudentDao studentDao;

    //DTO 로 넣을 땐 student_id 가 없을 것 - 요청에 안 넣어줬기 때문
    //DAO 의 insert 수행 시 id 를 만들어서 넣어줌
    public Student save(StudentDto studentDto) {
        Student student = studentDto.toEntity();    //Dto 에 정의한 toEntity 이용해서 Dto -> Entity 변환
        studentDao.insert(student);                 //Dao 에 insert 엔티티
        return student;
    }
}
