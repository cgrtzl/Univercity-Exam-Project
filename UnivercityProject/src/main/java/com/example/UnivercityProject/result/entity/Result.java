package com.example.UnivercityProject.result.entity;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.student.entity.Student;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class Result {

    @MongoId
    private String id;
    @Field
    private int score;
    @DBRef
    @Field
    private Exam exam;
    @DBRef
    @Field
    private Student student;
}
