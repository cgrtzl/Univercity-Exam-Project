package com.example.UnivercityProject.result.repo;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.result.entity.Result;
import com.example.UnivercityProject.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepo extends MongoRepository<Result,String> {
    List<Result> findByStudentId(String studentId);

    List<Result> findByExamAndStudent(Exam exam, Student student);

    List<Result> findByExam(Exam exam);

    List<Result> findByStudent(Student student);
}
