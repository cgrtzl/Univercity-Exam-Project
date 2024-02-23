import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Choice } from 'src/app/model/choice.model';
import { Exam } from 'src/app/model/exam.model';
import { Question } from 'src/app/model/question.model';
import { Result } from 'src/app/model/result.model';
import { Student } from 'src/app/model/student.model';
import { AdminService } from 'src/app/services/admin/admin.service';
import { ExamService } from 'src/app/services/exam/exam.service';
import { StudentService } from 'src/app/services/student/student.service';
declare let alertify: any;
declare let bootstrap: any;

@Component({
  selector: 'app-student-exams',
  templateUrl: './student-exams.component.html',
  styleUrls: ['./student-exams.component.scss']
})
export class StudentExamsComponent implements OnInit{
  currentUser: Student | any
  activeContent = '';
  usersExams: Exam[] = [];
  examId: string | undefined
  status: string | undefined
  examStatuses: { [examId: string]: string } = {};
  selectedExam: Exam | undefined;
  questionsForExam: Question[] = [];
  selectedChoices: { [questionId: string]: string } = {};
  choices: Choice[] = [];
  questions: Question[] = [];
  timeRemaining: number = 0;
  timerInterval: any;
  results: Result[] = [];
  
  takeExamModal: any;
  

  constructor(
    private studentService: StudentService,
    private router: Router,
    private examService: ExamService,
    private adminService: AdminService
  ){}


  ngOnInit(): void {
  this.loadUsersExam();
  this.getQuestions();
  this.getChoices();
  this.getResults();
  this.takeExamModal = new bootstrap.Modal(document.getElementById('takeExamModal'));
  }

  loadUsersExam() {
    this.studentService.getAssignedExams(this.currentUser.id).subscribe(
      (response: any) => {
        this.usersExams = response; // Öğrencinin sınavlarını usersExams içine atıyoruz
        this.checkExamStatusForUsersExams();
        
      },
      (error) => {
        console.error(error);
      }
    );
  }

  checkExamStatusForUsersExams() {
    this.usersExams.forEach((exam: any) => {
      this.examService.isExamActive(exam.id!).subscribe(
        (status) => {
          this.examStatuses[exam.id] = status ? "Active" : "Inactive";
        },
        (error) => {
          console.error(error);
        }
      );
    });
  }

  startExam(exam: Exam) {
    alertify.confirm(
      `Start ${exam.name} Exam`,
      'Are you sure to start the exam? You can only take this exam once, make sure your connection is stable.',
      () => {
        this.openTakeExamModal(exam)
      },
      () => {
      }
    );
  }

  openTakeExamModal(exam: Exam) {
    this.selectedExam = exam;
    this.getQuestionsForExam(exam);
    this.takeExamModal.show();
    this.startTimer(exam.duration);
  }

  countSelectedChoices(): number {
    let count = 0;
    for (const questionId in this.selectedChoices) {
      if (this.selectedChoices.hasOwnProperty(questionId) && this.selectedChoices[questionId]) {
        count++;
      }
    }
    return count;
  }
  
  startTimer(duration: number) {
    let timerElement = document.getElementById('timer');
    let timeRemaining = duration * 60; // Saniye cinsinden süre
  
    let timerInterval = setInterval(() => {
      let minutes = Math.floor(timeRemaining / 60);
      let seconds = timeRemaining % 60;
  
    
      timerElement!.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
      timeRemaining--;
  
      if (timeRemaining < 0) {
        clearInterval(timerInterval!);
        this.submitExam(); 
      }
    }, 1000); 
  }

  submitExam() {
    const result: any = {
      score: 0,
      exam: this.selectedExam,
      student: this.currentUser
    };
    result.score = this.calculateStudentScore();
  
    this.adminService.createResult(result).subscribe(
      (createdResult) => {
        alertify.success('You have successfully completed the exam, your score: ', result.score);
      },
      (error) => {
        console.error('Error: ', error);
        alertify.error('Error');
      }
    );
  
    this.takeExamModal.hide();
  }

  calculateStudentScore(): number {
    let correctCount = 0;
  
    for (const question of this.questionsForExam) {
      const selectedChoiceId = this.selectedChoices[question.id!];
      if (selectedChoiceId) {
        const selectedChoice = this.choices.find((choice) => choice.id === selectedChoiceId);
        if (selectedChoice && selectedChoice.correct) {
          correctCount++;
        }
      }
    }
  
    // Puanı hesaplayın ve yuvarlayarak döndürün
    const totalQuestions = this.questionsForExam.length;
    const score = (correctCount / totalQuestions) * 100;
  
    return Math.round(score);
  }

  getQuestionsForExam(exam: Exam): void {
    this.questionsForExam = this.questions.filter((question) => question.exam.id === exam.id);
  }

  getResultScoreForExam(exam: Exam): string {
    if (this.currentUser) {
      const result = this.results.find((result) => result.exam.id === exam.id && result.student.id === this.currentUser.id);
      return result ? result.score.toString() : 'N/A';
    }
    return 'N/A';
  }

  getResults() {
    this.adminService.getAllResults().subscribe(
      (results) => {
        this.results = results;
      },
      (error) => {
        console.error('Error loading results', error);
      }
      );
  }

  getQuestions() {
    this.adminService.getAllQuestions().subscribe(
      (questions) => {
        this.questions = questions;
      },
      (error) => {
        console.error('Error loading questions:', error);
      }
    );
  }
  getChoicesForQuestion(question: Question): Choice[] {
    return this.choices.filter(choice => choice.question.id === question.id);
  }
  getChoices(): void {
    this.adminService.getAllChoices().subscribe(
      (choices) => {
        this.choices = choices;
      },
      (error) => {
        console.error('Error loading choices:', error);
      }
    );
  }

  goToStudentProfile(){
    this.router.navigate(['/student-panel/profile'])
  }

  logout() {this.router.navigate(['/']);}
}
