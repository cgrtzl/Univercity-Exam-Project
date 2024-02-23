package com.example.UnivercityProject.result.controller;

import com.example.UnivercityProject.question.entity.Question;
import com.example.UnivercityProject.result.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/student-score")
    public ResponseEntity<Integer> getStudentScore(@RequestParam("studentId") String studentId,
                                                   @RequestParam("examId") String examId) {
        Map<Long, Long> selectedChoices = new HashMap<>();
        List<Question> questions = new ArrayList<>(); // questions listesini doldurun
        int studentScore = resultService.calculateStudentScore(selectedChoices, questions);

        return new ResponseEntity<>(studentScore, HttpStatus.OK);
    }
}
