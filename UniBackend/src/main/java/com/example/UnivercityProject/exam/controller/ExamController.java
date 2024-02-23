package com.example.UnivercityProject.exam.controller;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/{examId}/isActive")
    public boolean isExamActive(@PathVariable String examId) {
        Exam exam = examService.getExamById(examId);
        return examService.isExamActive(exam);
    }
}
