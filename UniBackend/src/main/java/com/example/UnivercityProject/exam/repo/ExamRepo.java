package com.example.UnivercityProject.exam.repo;

import com.example.UnivercityProject.classroom.entity.Classroom;
import com.example.UnivercityProject.exam.entity.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamRepo extends MongoRepository<Exam,String > {
    List<Exam> findByClassroom(Classroom classroom);

    Exam getExamById(String examId);
}
