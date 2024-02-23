package com.example.UnivercityProject.student.controller;

import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.result.entity.Result;
import com.example.UnivercityProject.student.entity.Student;
import com.example.UnivercityProject.student.repo.StudentRepo;
import com.example.UnivercityProject.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/{studentId}/name")
    public ResponseEntity<String> getStudentName(@PathVariable String studentId) {
        String studentName = studentService.getStudentNameById(studentId);
        if (studentName != null) {
            return new ResponseEntity<>(studentName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{studentId}/update-password")
    public ResponseEntity<Map<String, Object>> updatePassword(
            @PathVariable String studentId,
            @RequestBody Map<String, String> request) {
        String newPassword = request.get("newPassword");
        boolean updated = studentService.updatePassword(studentId, newPassword);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("message", "Password updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Student not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{studentId}/exams")
    public ResponseEntity<List<Exam>> getExamsAssignedToStudent(@PathVariable String studentId) {
        List<Exam> exams = studentService.getExamsAssignedToStudent(studentId);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/exams/{examId}/can-take")
    public ResponseEntity<Boolean> canStudentTakeExam(@PathVariable String studentId, @PathVariable String examId) {
        boolean canTakeExam = studentService.canStudentTakeExam(studentId, examId);
        return new ResponseEntity<>(canTakeExam, HttpStatus.OK);
    }

    @GetMapping("/{studentId}/results")
    public ResponseEntity<List<Result>> getResultsByStudentId(@PathVariable String studentId) {
        List<Result> results = studentService.getResultsByStudentId(studentId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
