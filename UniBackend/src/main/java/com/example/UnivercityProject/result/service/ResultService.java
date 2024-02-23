package com.example.UnivercityProject.result.service;

import com.example.UnivercityProject.choice.entity.Choice;
import com.example.UnivercityProject.choice.repo.ChoiceRepo;
import com.example.UnivercityProject.question.entity.Question;
import com.example.UnivercityProject.question.repo.QuestionRepo;
import com.example.UnivercityProject.result.repo.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private ResultRepo resultRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ChoiceRepo choiceRepo;

    public int calculateStudentScore(Map<Long, Long> selectedChoices, List<Question> questions) {
        int correctCount = 0;
        for (Question question : questions) {
            Long selectedChoiceId = selectedChoices.get(Long.valueOf(question.getId()));
            if (selectedChoiceId != null) {
                List<Choice> choices = choiceRepo.findByQuestion(question);
                for (Choice choice : choices) {
                    if (choice.getId().equals(selectedChoiceId) && choice.isCorrect()) {
                        correctCount++;
                        break;}}}}
        int totalQuestions = questions.size();
        double score = ((double) correctCount / totalQuestions) * 100;
        return (int) Math.round(score);
    }

}
