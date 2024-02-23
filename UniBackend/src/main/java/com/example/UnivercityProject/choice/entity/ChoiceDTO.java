package com.example.UnivercityProject.choice.entity;

import com.example.UnivercityProject.question.entity.QuestionDTO;
import lombok.Data;

@Data
public class ChoiceDTO {

    private String id;
    private String name;
    private String description;
    private QuestionDTO question;
    private boolean correct;
}
