package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
//    private int courseId;
    private String courseCode;
    private String courseName;
    private int professorId;
    private int credit;
    private int enrollmentCapacity;
    private String classroom;

    public Course toEntity() {
        return Course.builder()
//                .courseId(courseId)
                .courseCode(courseCode)
                .courseName(courseName)
                .professorId(professorId)
                .credit(credit)
                .enrollmentCapacity(enrollmentCapacity)
                .classroom(classroom)
                .build();
    }

}
