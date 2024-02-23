package com.example.UnivercityProject.classroom.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
public class Classroom {
    @MongoId
    private String id;
    @Field
    private String name;
}
