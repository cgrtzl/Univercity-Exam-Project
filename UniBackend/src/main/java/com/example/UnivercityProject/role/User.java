package com.example.UnivercityProject.role;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

public class User {
    @MongoId
    private String id;
    @Field
    private String email;
}
