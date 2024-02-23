package com.example.UnivercityProject.exam.entity;

import com.example.UnivercityProject.classroom.entity.ClassroomMapperIMPL;

public class ExamMapperIMPL {

    public static ExamDTO examToDTO(Exam exam){

        ExamDTO examDTO = new ExamDTO();

        examDTO.setId(exam.getId());
        examDTO.setName(exam.getName());
        examDTO.setStartDate(exam.getStartDate());
        examDTO.setEndDate(exam.getEndDate());
        examDTO.setDuration(exam.getDuration());
        examDTO.setClassroom(ClassroomMapperIMPL.classroomToDTO(exam.getClassroom()));

        return examDTO;
    }

    public static Exam examToDocument(ExamDTO examDTO){

        Exam exam = new Exam();

        exam.setId(examDTO.getId());
        exam.setName(examDTO.getName());
        exam.setStartDate(examDTO.getStartDate());
        exam.setEndDate(examDTO.getEndDate());
        exam.setDuration(examDTO.getDuration());
        exam.setClassroom(ClassroomMapperIMPL.classroomToDocument(examDTO.getClassroom()));

        return exam;
    }
}
