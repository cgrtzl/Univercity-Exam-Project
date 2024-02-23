package com.example.UnivercityProject.result.entity;

import com.example.UnivercityProject.exam.entity.ExamDTO;
import com.example.UnivercityProject.student.entity.StudentDTO;
import lombok.Data;

@Data
public class ResultDTO {

    private String id;
    private int score;
    private ExamDTO exam;
    private StudentDTO student;

}
