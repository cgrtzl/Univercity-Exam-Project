package com.example.UnivercityProject.question.entity;

import com.example.UnivercityProject.exam.entity.ExamMapperIMPL;

public class QuestionMapperIMPL {

    public static QuestionDTO questionToDTO(Question question){

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setExam(ExamMapperIMPL.examToDTO(question.getExam()));
        questionDTO.setQuestionNumber(question.getQuestionNumber());
        return questionDTO;
    }

    public static Question questionToDocument(QuestionDTO questionDTO){

        Question question = new Question();

        question.setId(questionDTO.getId());
        question.setTitle(questionDTO.getTitle());
        question.setExam(ExamMapperIMPL.examToDocument(questionDTO.getExam()));
        question.setQuestionNumber(questionDTO.getQuestionNumber());
        return question;
    }
}
