package com.example.UnivercityProject.student.service;

import com.example.UnivercityProject.classroom.entity.Classroom;
import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.exam.repo.ExamRepo;
import com.example.UnivercityProject.result.entity.Result;
import com.example.UnivercityProject.result.repo.ResultRepo;
import com.example.UnivercityProject.student.entity.Student;
import com.example.UnivercityProject.student.entity.StudentDTO;
import com.example.UnivercityProject.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private ResultRepo resultRepo;

    public String getStudentNameById(String studentId) {
        Optional<Student> optionalStudent = studentRepo.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            return student.getName();
        }
        return null;
    }

    public boolean updatePassword(String studentId, String newPassword) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student != null) {
            student.setPassword(newPassword);
            studentRepo.save(student);
            return true;
        }
        return false;
    }

    // 1. Kendi sınıfına atanmış olan sınavları listelemek
    public List<Exam> getExamsAssignedToStudent(String studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student != null && student.getClassroom() != null) {
            Classroom classroom = student.getClassroom();
            return examRepo.findByClassroom(classroom);
        }
        return Collections.emptyList();
    }

    // 2. Bu atanan sınavlardan tarihi gelen sınava girebilmek
    public boolean canStudentTakeExam(String studentId, String examId) {
        Exam exam = examRepo.findById(examId).orElse(null);
        if (exam != null && exam.getStartDate() != null) {
            Date currentDate = new Date();
            return exam.getStartDate().after(currentDate);
        }
        return false;
    }

    // 3. Bitirdiği sınavın notlarını görebilmek
    public List<Result> getResultsByStudentId(String studentId) {
        return resultRepo.findByStudentId(studentId);
    }
}
