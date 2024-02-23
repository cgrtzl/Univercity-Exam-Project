package com.example.UnivercityProject.result.entity;

import com.example.UnivercityProject.exam.entity.ExamMapperIMPL;
import com.example.UnivercityProject.student.entity.StudentMapperIMPL;

public class ResultMapperIMPL {

    public static ResultDTO resultToDTO(Result result){

        ResultDTO resultDTO = new ResultDTO();

        resultDTO.setId(result.getId());
        resultDTO.setScore(result.getScore());
        resultDTO.setExam(ExamMapperIMPL.examToDTO(result.getExam()));
        resultDTO.setStudent(StudentMapperIMPL.studentToDTO(result.getStudent()));

        return resultDTO;
    }

    public static Result resultToDocument(ResultDTO resultDTO){

        Result result = new Result();

        result.setId(resultDTO.getId());
        result.setScore(resultDTO.getScore());
        result.setExam(ExamMapperIMPL.examToDocument(resultDTO.getExam()));
        result.setStudent(StudentMapperIMPL.studentToDocument(resultDTO.getStudent()));

        return result;
    }

}
