package com.example.UnivercityProject.classroom.entity;

public class ClassroomMapperIMPL{

    public static ClassroomDTO classroomToDTO(Classroom classroom) {

        ClassroomDTO classroomDTO = new ClassroomDTO();

        classroomDTO.setId(classroom.getId());
        classroomDTO.setName(classroom.getName());

        return classroomDTO;
    }


    public static Classroom classroomToDocument(ClassroomDTO classroomDTO) {

        Classroom classroom = new Classroom();

        classroom.setId(classroomDTO.getId());
        classroom.setName(classroomDTO.getName());

        return classroom;
    }
}
