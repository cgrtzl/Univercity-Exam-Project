package com.example.UnivercityProject.exam.entity;

import com.example.UnivercityProject.classroom.entity.Classroom;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Data
@Document
public class Exam {

    @MongoId
    private String id;
    @Field
    private String name;
    @Field
    private Date startDate;
    @Field
    private Date endDate;
    @Field
    private int duration;
    @DBRef
    @Field
    private Classroom classroom;
}
