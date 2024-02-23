package com.example.UnivercityProject.admin.controller;

import com.example.UnivercityProject.admin.repo.AdminRepo;
import com.example.UnivercityProject.classroom.entity.Classroom;
import com.example.UnivercityProject.classroom.repo.ClassroomRepo;
import com.example.UnivercityProject.exam.repo.ExamRepo;
import com.example.UnivercityProject.question.repo.QuestionRepo;
import com.example.UnivercityProject.result.entity.Result;
import com.example.UnivercityProject.student.entity.Student;
import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.question.entity.Question;
import com.example.UnivercityProject.choice.entity.Choice;
import com.example.UnivercityProject.admin.service.AdminService;

import com.example.UnivercityProject.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ClassroomRepo classroomRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AdminRepo adminRepo;

    // Classroom CRUD
    @PostMapping("/classrooms")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        Classroom createdClassroom = adminService.createClassroom(classroom);
        return new ResponseEntity<>(createdClassroom, HttpStatus.CREATED);
    }

    @GetMapping("/classrooms")
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = adminService.getAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/classrooms/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable String classroomId) {
        Classroom classroom = adminService.getClassroomById(classroomId);
        if (classroom != null) {
            return new ResponseEntity<>(classroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/classrooms/{classroomId}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable String classroomId, @RequestBody Map<String, Object> requestBody) {
        String updatedName = (String) requestBody.get("name");
        Classroom classroom = adminService.updateClassroom(classroomId, updatedName);
        if (classroom != null) {
            return new ResponseEntity<>(classroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/classrooms/{classroomId}")
    public ResponseEntity<Void> deleteClassroomWithRelatedEntities(@PathVariable String classroomId) {
        Classroom classroom = classroomRepo.findById(classroomId).orElse(null);
        if (classroom != null) {
            adminService.deleteClassroomWithRelatedEntities(classroom);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Student CRUD
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = adminService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = adminService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        Student student = adminService.getStudentById(studentId);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @RequestBody Student updatedStudent) {
        Student student = adminService.updateStudent(studentId, updatedStudent);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Void> deleteStudentWithRelatedEntities(@PathVariable String studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student != null) {
            adminService.deleteStudentWithRelatedEntities(student);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Exam CRUD
    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = adminService.createExam(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = adminService.getAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exams/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable String examId) {
        Exam exam = adminService.getExamById(examId);
        if (exam != null) {
            return new ResponseEntity<>(exam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/exams/{examId}")
    public ResponseEntity<Exam> updateExam(@PathVariable String examId, @RequestBody Exam updatedExam) {
        Exam exam = adminService.updateExam(examId, updatedExam);
        if (exam != null) {
            return new ResponseEntity<>(exam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/exams/{examId}")
    public ResponseEntity<Void> deleteExamByIdWithRelatedEntities(@PathVariable String examId) {
        Exam exam = examRepo.findById(examId).orElse(null);
        if (exam != null) {
            adminService.deleteExamWithRelatedEntities(exam);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Question CRUD
    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = adminService.createQuestion(question);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = adminService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable String questionId) {
        Question question = adminService.getQuestionById(questionId);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/questions/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String questionId, @RequestBody Question updatedQuestion) {
        Question question = adminService.updateQuestion(questionId, updatedQuestion);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable String questionId) {
        Question question = questionRepo.findById(questionId).orElse(null);
        if (question != null) {
            adminService.deleteQuestionWithRelatedEntities(question);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Choice CRUD
    @PostMapping("/choices")
    public ResponseEntity<Choice> createChoice(@RequestBody Choice choice) {
        Choice createdChoice = adminService.createChoice(choice);
        return new ResponseEntity<>(createdChoice, HttpStatus.CREATED);
    }

    @GetMapping("/choices")
    public ResponseEntity<List<Choice>> getAllChoices() {
        List<Choice> choices = adminService.getAllChoices();
        return new ResponseEntity<>(choices, HttpStatus.OK);
    }

    @GetMapping("/choices/{choiceId}")
    public ResponseEntity<Choice> getChoiceById(@PathVariable String choiceId) {
        Choice choice = adminService.getChoiceById(choiceId);
        if (choice != null) {
            return new ResponseEntity<>(choice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/choices/{choiceId}")
    public ResponseEntity<Choice> updateChoice(@PathVariable String choiceId, @RequestBody Choice updatedChoice) {
        Choice choice = adminService.updateChoice(choiceId, updatedChoice);
        if (choice != null) {
            return new ResponseEntity<>(choice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/choices/{choiceId}")
    public ResponseEntity<Void> deleteChoiceById(@PathVariable String choiceId) {
        adminService.deleteChoiceById(choiceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Result CRUD
    @PostMapping("/results")
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result createdResult = adminService.createResult(result);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @GetMapping("/results")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = adminService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/results/{resultId}")
    public ResponseEntity<Result> getResultById(@PathVariable String resultId) {
        Result result = adminService.getResultById(resultId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/results/{resultId}")
    public ResponseEntity<Result> updateResult(@PathVariable String resultId, @RequestBody Result updatedResult) {
        Result result = adminService.updateResult(resultId, updatedResult);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/results/{resultId}")
    public ResponseEntity<Void> deleteResultById(@PathVariable String resultId) {
        adminService.deleteResultById(resultId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
