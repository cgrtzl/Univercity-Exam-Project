package com.example.UnivercityProject.choice.entity;


import com.example.UnivercityProject.question.entity.Question;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class Choice {

    @MongoId
    private String id;
    @Field
    private String name;
    @Field
    private String description;
    @DBRef
    @Field
    private Question question;
    @Field
    private boolean correct;

}
