package com.example.UnivercityProject.student.entity;

import com.example.UnivercityProject.classroom.entity.ClassroomDTO;
import com.example.UnivercityProject.role.Role;
import lombok.Data;
@Data
public class StudentDTO {
    private String id;
    private String name;
    private String number;
    private String password;
    private String email;
    private ClassroomDTO classroom;
    private Role role;
}
