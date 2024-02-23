package com.example.UnivercityProject.question.entity;

import com.example.UnivercityProject.exam.entity.Exam;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class Question {

    @MongoId
    private String id;
    @Field
    private String title;
    @Field
    private String questionNumber;
    @DBRef
    @Field
    private Exam exam;
}
