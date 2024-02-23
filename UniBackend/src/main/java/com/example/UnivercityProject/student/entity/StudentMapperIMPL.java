package com.example.UnivercityProject.student.entity;

import com.example.UnivercityProject.classroom.entity.ClassroomMapperIMPL;

public class StudentMapperIMPL{

    public static StudentDTO studentToDTO(Student student) {

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setNumber(student.getNumber());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setClassroom(ClassroomMapperIMPL.classroomToDTO(student.getClassroom()));
        studentDTO.setRole(student.getRole());

        return studentDTO;
    }

    public static Student studentToDocument(StudentDTO studentDTO) {

        Student student = new Student();

        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setNumber(studentDTO.getNumber());
        student.setPassword(studentDTO.getPassword());
        student.setEmail(studentDTO.getEmail());
        student.setClassroom(ClassroomMapperIMPL.classroomToDocument(studentDTO.getClassroom()));
        student.setRole(studentDTO.getRole());

        return student;
    }
}
