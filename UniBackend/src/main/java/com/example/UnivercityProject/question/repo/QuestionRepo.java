package com.example.UnivercityProject.question.repo;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.question.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends MongoRepository<Question,String > {
    List<Question> findByExam(Exam exam);

    List<Question> getQuestionsByExam(Exam exam);
}
