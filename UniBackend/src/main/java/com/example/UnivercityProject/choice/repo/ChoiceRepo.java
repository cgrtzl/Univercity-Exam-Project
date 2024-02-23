package com.example.UnivercityProject.choice.repo;

import com.example.UnivercityProject.choice.entity.Choice;
import com.example.UnivercityProject.question.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepo extends MongoRepository<Choice,String> {
    List<Choice> findByQuestion(Question question);
}
