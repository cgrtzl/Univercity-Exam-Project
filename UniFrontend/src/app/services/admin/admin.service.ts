import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Classroom } from '../../model/classroom.model';
import { Student } from 'src/app/model/student.model';
import { Exam } from 'src/app/model/exam.model';
import { Question } from 'src/app/model/question.model';
import { Choice } from 'src/app/model/choice.model';
import { Result } from 'src/app/model/result.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private baseUrl = 'http://localhost:8080/admin'; 

  constructor(private http: HttpClient) { }

  // Classroom CRUD
  createClassroom(classroom: Classroom): Observable<Classroom> {
    return this.http.post<Classroom>(`${this.baseUrl}/classrooms`, classroom);
  }
  getClassrooms(): Observable<Classroom[]> {
    return this.http.get<Classroom[]>(`${this.baseUrl}/classrooms`);
  }
  updateClassroom(classroom: Classroom): Observable<Classroom> {
    return this.http.put<Classroom>(`${this.baseUrl}/classrooms/${classroom.id}`, classroom);
  }
  deleteClassroom(classroom: Classroom): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/classrooms/${classroom.id}`);
  }
  // Student CRUD
  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.baseUrl}/students`, student);
  }
  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.baseUrl}/students`);
  }
  getStudentById(studentId: string): Observable<Student> {
    return this.http.get<Student>(`${this.baseUrl}/students/${studentId}`);
  }
  updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.baseUrl}/students/${student.id}`,student);
  }
  deleteStudentWithRelatedEntities(student: Student): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/students/${student.id}`);
  }
  // Exam CRUD 
  createExam(exam: Exam): Observable<Exam> {
    return this.http.post<Exam>(`${this.baseUrl}/exams`, exam);
  }
  getAllExams(): Observable<Exam[]> {
    return this.http.get<Exam[]>(`${this.baseUrl}/exams`);
  }
  getExamById(examId: string): Observable<Exam> {
    return this.http.get<Exam>(`${this.baseUrl}/exams/${examId}`);
  }
  updateExam(exam: Exam): Observable<Exam> {
    return this.http.put<Exam>(`${this.baseUrl}/exams/${exam.id}`, exam);
  }
  deleteExamWithRelatedEntities(exam: Exam): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/exams/${exam.id}`);
  }
  // Question CRUD
  createQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(`${this.baseUrl}/questions`, question);
  }
  getAllQuestions(): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}/questions`);
  }
  getQuestionById(questionId: string): Observable<Question> {
    return this.http.get<Question>(`${this.baseUrl}/questions/${questionId}`);
  }
  updateQuestion(question: Question): Observable<Question> {
    return this.http.put<Question>(`${this.baseUrl}/questions/${question.id}`, question);
  }
  deleteQuestion(question: Question): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/questions/${question.id}`);
  }
  // Choice CRUD
  createChoice(choice: Choice): Observable<Choice> {  
    return this.http.post<Choice>(`${this.baseUrl}/choices`, choice);
  }
  getAllChoices(): Observable<Choice[]> {
    return this.http.get<Choice[]>(`${this.baseUrl}/choices`);
  }
  getChoiceById(choiceId: string): Observable<Choice> {
    return this.http.get<Choice>(`${this.baseUrl}/choices/${choiceId}`);
  }
  updateChoice(choice: Choice): Observable<Choice> {
    return this.http.put<Choice>(`${this.baseUrl}/choices/${choice.id}`, choice);
  }
  deleteChoice(choice: Choice): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/choices/${choice.id}`);
  }
  // Result CRUD
  createResult(result: Result): Observable<Result> {  
    return this.http.post<Result>(`${this.baseUrl}/results`, result);
  }
  getAllResults(): Observable<Result[]> {
    return this.http.get<Result[]>(`${this.baseUrl}/results`);
  }
  getResultById(resultId: string): Observable<Result> {
    return this.http.get<Result>(`${this.baseUrl}/results/${resultId}`);
  }
  updateResult(result: Result): Observable<Result> {
    return this.http.put<Result>(`${this.baseUrl}/results/${result.id}`, result);
  }
  deleteResult(result: Result): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/results/${result.id}`);
  }
  getStudentScore(studentId: string, examId: string): Observable<number> {
    return this.http.get<number>(`http://localhost:8080/result/student-score?studentId=${studentId}&examId=${examId}`);
  }
  
}
