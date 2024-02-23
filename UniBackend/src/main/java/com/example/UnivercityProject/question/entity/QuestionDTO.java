package com.example.UnivercityProject.question.entity;

import com.example.UnivercityProject.exam.entity.ExamDTO;
import lombok.Data;

@Data
public class QuestionDTO {

    private String id;
    private String title;
    private String questionNumber;
    private ExamDTO exam;

}
