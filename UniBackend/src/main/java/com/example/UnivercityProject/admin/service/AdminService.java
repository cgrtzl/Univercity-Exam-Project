package com.example.UnivercityProject.admin.service;

import com.example.UnivercityProject.admin.repo.AdminRepo;
import com.example.UnivercityProject.choice.entity.Choice;
import com.example.UnivercityProject.choice.repo.ChoiceRepo;
import com.example.UnivercityProject.classroom.entity.Classroom;
import com.example.UnivercityProject.classroom.repo.ClassroomRepo;
import com.example.UnivercityProject.exam.entity.Exam;
import com.example.UnivercityProject.exam.repo.ExamRepo;
import com.example.UnivercityProject.question.entity.Question;
import com.example.UnivercityProject.question.repo.QuestionRepo;
import com.example.UnivercityProject.result.entity.Result;
import com.example.UnivercityProject.result.repo.ResultRepo;
import com.example.UnivercityProject.student.entity.Student;
import com.example.UnivercityProject.student.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ClassroomRepo classroomRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private ChoiceRepo choiceRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private ResultRepo resultRepo;

    // Classroom CRUD
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepo.save(classroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepo.findAll();
    }

    public Classroom getClassroomById(String classroomId) {
        return classroomRepo.findById(classroomId).orElse(null);
    }

    public Classroom updateClassroom(String classroomId, String updatedClassroomName) {
        Classroom classroom = classroomRepo.findById(classroomId).orElse(null);
        if (classroom != null) {
            classroom.setName(updatedClassroomName);
            return classroomRepo.save(classroom);
        }
        return null;
    }

    public void deleteClassroomWithRelatedEntities(Classroom classroom) {
        // Classroom nesnesine ait tüm examları bul
        List<Exam> exams = examRepo.findByClassroom(classroom);
        // Her exam için related questionları bul
        for (Exam exam: exams){
            List<Question> questions = questionRepo.findByExam(exam);
            // Her question için related choiceları bul ve sil ardından question nesnesini sil
            for (Question question: questions) {
                List<Choice> choices = choiceRepo.findByQuestion(question);
                choiceRepo.deleteAll(choices);
                questionRepo.delete(question);
            }
            examRepo.deleteAll(exams);
            List<Result> results = resultRepo.findByExam(exam);
            resultRepo.deleteAll(results);
        }
        List<Student> students = studentRepo.findByClassroom(classroom);
        for (Student student: students) {
            List<Result> results = resultRepo.findByStudent(student);
            resultRepo.deleteAll(results);
            studentRepo.delete(student);
        }
        classroomRepo.delete(classroom);
    }

    // Student CRUD
    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(String studentId) {
        return studentRepo.findById(studentId).orElse(null);
    }

    public Student updateStudent(String studentId, Student updatedStudent) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setNumber(updatedStudent.getNumber());
            student.setPassword(updatedStudent.getPassword());
            student.setClassroom(updatedStudent.getClassroom());
            return studentRepo.save(student);
        }
        return null;
    }

    public void deleteStudentWithRelatedEntities(Student student) {
        List<Result> results = resultRepo.findByStudent(student);
        for (Result result: results) {
            resultRepo.delete(result);
        }
        studentRepo.delete(student);
    }

    // Exam CRUD
    public Exam createExam(Exam exam) {
        return examRepo.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }

    public Exam getExamById(String examId) {
        return examRepo.findById(examId).orElse(null);
    }

    public Exam updateExam(String examId, Exam updatedExam) {
        Exam exam = examRepo.findById(examId).orElse(null);
        if (exam != null) {
            exam.setName(updatedExam.getName());
            exam.setStartDate(updatedExam.getStartDate());
            exam.setEndDate(updatedExam.getEndDate());
            exam.setDuration(updatedExam.getDuration());
            exam.setClassroom(updatedExam.getClassroom());
            return examRepo.save(exam);
        }
        return null;
    }

    public void deleteExamWithRelatedEntities(Exam exam) {
        // Exam nesnesine ait tüm questionları bul
        List<Question> questions = questionRepo.findByExam(exam);

        // Her question için related choice'ları bul ve sil ardından question nesnesini sil
        for (Question question : questions) {
            List<Choice> choices = choiceRepo.findByQuestion(question);
            choiceRepo.deleteAll(choices);
            questionRepo.delete(question);
        }

        // Exam nesnesine ait tüm resultları bul ve sil
        List<Result> results = resultRepo.findByExam(exam);
        resultRepo.deleteAll(results);

        // Exam nesnesini sil
        examRepo.delete(exam);
    }

    // Question CRUD
    public Question createQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question getQuestionById(String questionId) {
        return questionRepo.findById(questionId).orElse(null);
    }

    public Question updateQuestion(String questionId, Question updatedQuestion) {
        Question question = questionRepo.findById(questionId).orElse(null);
        if (question != null) {
            question.setTitle(updatedQuestion.getTitle());
            question.setQuestionNumber(updatedQuestion.getQuestionNumber());
            question.setExam(updatedQuestion.getExam());
            return questionRepo.save(question);
        }
        return null;
    }

    public void deleteQuestionWithRelatedEntities(Question question) {
            List<Choice> choices = choiceRepo.findByQuestion(question);
        for (Choice choice: choices) {
            choiceRepo.delete(choice);
        }
        questionRepo.delete(question);
    }

    // Choice CRUD
    public Choice createChoice(Choice choice) {
        return choiceRepo.save(choice);
    }

    public List<Choice> getAllChoices() {
        return choiceRepo.findAll();
    }

    public Choice getChoiceById(String choiceId) {
        return choiceRepo.findById(choiceId).orElse(null);
    }

    public Choice updateChoice(String choiceId, Choice updatedChoice) {
        Choice choice = choiceRepo.findById(choiceId).orElse(null);
        if (choice != null) {
            choice.setName(updatedChoice.getName());
            choice.setDescription(updatedChoice.getDescription());
            choice.setCorrect(updatedChoice.isCorrect());
            choice.setQuestion(updatedChoice.getQuestion());
            return choiceRepo.save(choice);
        }
        return null;
    }

    public void deleteChoiceById(String choiceId) {
        choiceRepo.deleteById(choiceId);
    }

    // Result CRUD
    public Result createResult(Result result) {return resultRepo.save(result);}

    public List<Result> getAllResults() { return resultRepo.findAll();}

    public Result getResultById(String resultId) {
        return resultRepo.findById(resultId).orElse(null);
    }

    public Result updateResult(String resultId, Result updatedResult) {
        Result result = resultRepo.findById(resultId).orElse(null);
        if (result != null) {
            result.setScore(updatedResult.getScore());
            result.setStudent(updatedResult.getStudent());
            result.setExam(updatedResult.getExam());
            return resultRepo.save(result);
        }
        return null;
    }

    public void deleteResultById(String resultId) {
        resultRepo.deleteById(resultId);
    }
}