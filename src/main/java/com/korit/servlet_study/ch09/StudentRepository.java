package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentRepository {
    private List<Student> studentList = new ArrayList<>();
    private int autoId = 2025001;
    private ObjectMapper om;

    //servlet 에서 생성한 student 를 매개변수로 받아옴
    public void insert(Student student) {
        student.setId(autoId++);
        studentList.add(student);
        System.out.println("추가한 학생: " + student);
        System.out.println(studentList);

    }

    public List<Student> findAllBySearchNameValue(String searchNameValue) {
        if (Objects.isNull(searchNameValue)) {
            return studentList;
        }
        return studentList.stream()
                .filter(student -> student.getName().contains(searchNameValue))
                .toList();
    }
}
