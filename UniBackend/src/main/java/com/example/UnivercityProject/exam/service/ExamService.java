package com.example.UnivercityProject.exam.service;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.exam.repo.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepo repo;

    public boolean isExamActive(Exam exam) {
        Date now = new Date();
        return now.after(exam.getStartDate()) && now.before(exam.getEndDate());
    }

    public Exam getExamById(String examId) {
        Optional<Exam> examOptional = repo.findById(examId);
        return examOptional.orElse(null);
    }

}
