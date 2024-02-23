package com.example.UnivercityProject.choice.entity;

import com.example.UnivercityProject.question.entity.QuestionMapperIMPL;

public class ChoiceMapperIMPL {

    public static ChoiceDTO choiceToDTO(Choice choice){

        ChoiceDTO choiceDTO = new ChoiceDTO();

        choiceDTO.setId(choice.getId());
        choiceDTO.setName(choice.getName());
        choiceDTO.setCorrect(choice.isCorrect());
        choiceDTO.setQuestion(QuestionMapperIMPL.questionToDTO(choice.getQuestion()));
        choiceDTO.setDescription(choice.getDescription());

        return choiceDTO;
    }

    public static Choice choiceToDocument(ChoiceDTO choiceDTO){

        Choice choice = new Choice();

        choice.setId(choiceDTO.getId());
        choice.setName(choiceDTO.getName());
        choice.setCorrect(choiceDTO.isCorrect());
        choice.setQuestion(QuestionMapperIMPL.questionToDocument(choiceDTO.getQuestion()));
        choice.setDescription(choiceDTO.getDescription());

        return choice;
    }
}
