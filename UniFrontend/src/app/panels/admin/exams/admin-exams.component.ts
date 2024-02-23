import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Choice } from 'src/app/model/choice.model';
import { Classroom } from 'src/app/model/classroom.model';
import { Exam } from 'src/app/model/exam.model';
import { Question } from 'src/app/model/question.model';
import { AdminService } from 'src/app/services/admin/admin.service';
declare let alertify: any;
declare let bootstrap: any;

@Component({
  selector: 'app-exams',
  templateUrl: './admin-exams.component.html',
  styleUrls: ['./admin-exams.component.scss']
})
export class ExamsComponent implements OnInit{
  activeContent = '';
  selectedExam: Exam | null = null;
  newExamName = '';
  newExamStartDate = new Date();
  newExamEndDate = new Date();
  newExamDuration = 0;
  selectedExamClassroom: Classroom | undefined = undefined;
  newExamClassroom: Classroom | undefined = undefined;
  exams: Exam[] = [];
  newExam: Exam | undefined = undefined;
  choices: Choice[] = [];
  questions: Question[] = [];
  choicesArray: Choice[] = [];
  currentQuestion: Question | undefined;
  currentQuestionNumber: number = 1;
  currentQuestionTitle: string = '';
  numberOfChoices: number = 2;
  newQuestionTitle = '';
  newQuestionNumber = 1;
  newQuestionExam: Exam | undefined = undefined;
  questionsForExam: Question[] = [];
  createChoiceButtonDisabled = true;
  classrooms: Classroom[] = [];

  createExamModal: any;
  editExamModal: any;
  customizeModal: any;
  examQuestionsModal: any;
  

  constructor(
    private adminService: AdminService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadExams();
    this.loadQuestions();
    this.loadChoices();
    this.loadClassrooms();
    this.editExamModal = new bootstrap.Modal(document.getElementById('editExamModal'));
    this.createExamModal = new bootstrap.Modal(document.getElementById('createExamModal'));
    this.customizeModal = new bootstrap.Modal(document.getElementById('customizeModal'));
    this.examQuestionsModal = new bootstrap.Modal(document.getElementById('examQuestionsModal'));
  }

  updateExam(): void {
    if (this.selectedExam && this.newExamName.trim() !== '' && this.newExamStartDate.toString().trim() !== '' && this.newExamEndDate && this.newExamDuration > 0 && this.newExamClassroom) {
      const updatedExam: Exam = {
        ...this.selectedExam,
        name: this.newExamName,
        startDate: this.newExamStartDate,
        endDate: this.newExamEndDate,
        duration: this.newExamDuration,
        classroom: this.newExamClassroom
      };
      if (updatedExam.name !== "") {
        this.adminService.updateExam(updatedExam).subscribe(
          () => {
            this.loadExams();
            alertify.success('Exam updated successfully.');
            this.editExamModal.hide(); // Modalı kapat
          },
          (error) => {
            alertify.error('Error updating exam. Please try again later.' + error);
          }
        );
      }
    } else {
      alertify.error('Please enter valid exam information.');
    }
  }
  loadExams() {
    this.adminService.getAllExams().subscribe(
      (exams) => {
        this.exams = exams;
      },
      (error) => {
        console.error('Error loading exams:', error);
      }
    );
  }
  createExam(): void {
    if (this.newExamName.trim() !== '' && this.newExamStartDate.toString().trim() !== '' && this.newExamEndDate.toString().trim() !== '' && this.newExamDuration > 0 && this.selectedExamClassroom) {
      const newExam: Exam = {
        name: this.newExamName,
        startDate: this.newExamStartDate,
        endDate: this.newExamEndDate,
        duration: this.newExamDuration,
        classroom: this.selectedExamClassroom
      };
  
      this.adminService.createExam(newExam).subscribe(
        (createdExam) => {
          alertify.success('Exam created: ' + createdExam.name);
          this.loadExams();
          this.createExamModal.hide();
        },
        (error) => {
          console.log(error);
          alertify.error('Error creating exam: ' + error);
        }
      );
    } else {
      alertify.error('Please enter valid exam information.');
    }
  }
  deleteExamWithRelatedEntities(exam: Exam): void {
    alertify.confirm('Are you sure to delete this exam with its all related entities?', () => {
        this.adminService.deleteExamWithRelatedEntities(exam).subscribe(
            () => {
              alertify.success('Exam with related entities deleted successfully!');
              this.loadExams();
            },
            (error) => {
              alertify.error('Error deleting exam with related entities!');
            }
          );
      })
      .set({ title: 'Delete Exam' })
      .set({ labels: { ok: 'Delete', cancel: 'Cancel' } });
  }
  selectClassroomForExam(classroom: Classroom): void {this.newExamClassroom = classroom;}
  selectExamClassroom(classroom: Classroom): void {this.selectedExamClassroom = classroom;} 
  //Exam Modals
  openEditExamModal(exam: Exam): void {
    this.selectedExam = exam;
    this.newExamName = exam.name;
    this.newExamStartDate = new Date(exam.startDate);
    this.newExamEndDate = new Date(exam.endDate);
    this.newExamDuration = exam.duration;
    this.newExamClassroom = exam.classroom;
    this.editExamModal.show();
  }  
  openCreateExamModal(): void {
    this.newExamName = '';
    this.newExamStartDate = new Date();
    this.newExamEndDate = new Date();
    this.newExamDuration = 0;
    this.selectedExamClassroom;
    this.createExamModal.show();
  }
  openCustomizeModal(exam: Exam) {
    this.selectedExam = exam;
    this.loadQuestionsForExam(exam);
    this.choicesArray = [];
    this.currentQuestionTitle = '';
    this.numberOfChoices = 2;
    this.newQuestionNumber = this.questionsForExam.length + 1;
    if (this.currentQuestionNumber > this.newQuestionNumber) {
      this.newQuestionNumber = this.currentQuestionNumber;
    }
    this.newQuestionTitle = '';
    this.newQuestionExam = exam;
    this.customizeModal.show();
  }
  onRangeChange(value: number) {
    this.numberOfChoices = value;
    this.updateChoicesArray();
  }
  updateChoicesArray() {
    this.choicesArray = [];
    for (let i = 0; i < this.numberOfChoices; i++) {
      const newChoice: Choice = {
        id: Math.random().toString(36).substring(7),
        name: String.fromCharCode(65 + i), // 'A', 'B', 'C', ...
        description: '',
        correct: false,
        question: this.currentQuestion!, // ! to ensure it's not null
      };
      this.choicesArray.push(newChoice);
    }
  }
  createQuestionWithChoices(): void {
    if (this.isFormValid()) {
      // Form doğruluk kontrolünü yaparak işlemi sadece form doğruysa gerçekleştir
      if (this.currentQuestion) {
        this.adminService.createQuestion(this.currentQuestion).subscribe(
          (createdQuestion) => {
            this.currentQuestion = createdQuestion;
            this.createChoices();
            this.router.navigate(['/admin-panel/exams'])
            this.customizeModal.show()

          },
          (error) => {
            console.log(error);
            alertify.error('Error creating question: ' + error);
          }
        );
      } else {
        alertify.error('No question to create');
      }
    }
  }
  isFormValid(): boolean {
    if (!this.currentQuestionTitle || !this.choicesArray.some(choice => choice.correct)) {
      alertify.warning('Please fill in all required fields.');
      return false;
    }
    return true;
  }
  onRadioChange(index: number) {
    for (let i = 0; i < this.choicesArray.length; i++) {
      if (i !== index) {
        this.choicesArray[i].correct = false;
      }
    }
  }
  getQuestionsForExam(exam: Exam): Question[] {
    return this.questions.filter(question => question.exam.id === exam.id)
  }
  getChoicesForQuestion(question: Question): Choice[] {
    return this.choices.filter(choice => choice.question.id === question.id);
  }
  openExamQuestionsModal(exam: Exam): void {
    if (exam) {
      this.loadQuestions;
      this.selectedExam = exam;
      this.questionsForExam = this.getQuestionsForExam(exam);
      this.examQuestionsModal.show();
    }
  }
  createChoices(): void {
    if (this.currentQuestion) {
      for (const choice of this.choicesArray) {
        choice.question = this.currentQuestion;
        this.adminService.createChoice(choice).subscribe(
          () => {
          },
          (error) => {
            console.log(error);
            alertify.error('Error creating choice: ' + error);
          }
        );
      }
      alertify.success("Question created!")
      // Reset everything after choices are created
      this.createChoiceButtonDisabled = true;
      this.choicesArray = [];
      this.currentQuestion = undefined;
      this.currentQuestionNumber++;
      this.newQuestionNumber = this.currentQuestionNumber;
      this.currentQuestionTitle = '';
      this.numberOfChoices = 2;
    }
  }
  loadQuestionsForExam(exam: Exam) {
    this.questionsForExam = this.questions.filter(question => question.exam.id === exam.id);
  }
  loadQuestions() {
    this.adminService.getAllQuestions().subscribe(
      (questions) => {
        this.questions = questions;
      },
      (error) => {
        console.error('Error loading questions:', error);
      }
    );
  }
  loadChoices(): void {
    this.adminService.getAllChoices().subscribe(
      (choices) => {
        this.choices = choices;
      },
      (error) => {
        console.error('Error loading choices:', error);
      }
    );
  }
  deleteQuestionWithRelatedEntities(question: Question){
    alertify.confirm('Are you sure to delete this question with its all related entities?', () => {
      this.adminService.deleteQuestion(question).subscribe(
          () => {
            alertify.success('Question with related entities deleted successfully!');
            this.loadQuestions();
            this.examQuestionsModal.hide();
          },
          (error) => {
            alertify.error('Error deleting question with related entities!');
          }
        );
    })
    .set({ title: 'Delete Question' })
    .set({ labels: { ok: 'Delete', cancel: 'Cancel' } });
  }
  saveQuestion(): void {
    if (this.selectedExam) {
      // Create a temporary question object
      this.currentQuestion = {
        questionNumber: this.newQuestionNumber,
        title: this.currentQuestionTitle,
        exam: this.selectedExam,
      };
      
      this.createChoiceInputs();
      this.createChoiceButtonDisabled = false;
    } else {
      console.error('No exam selected');
    }
  }
  createChoiceInputs(): void {
    this.choicesArray = [];
    for (let i = 0; i < this.numberOfChoices; i++) {
      const newChoice: Choice = {
        name: String.fromCharCode(65 + i),
        description: '',
        correct: false,
        question: this.currentQuestion!,
      };
      this.choicesArray.push(newChoice);
    }
  }
  loadClassrooms(): void {
    this.adminService.getClassrooms().subscribe(
      (classrooms) => {
        this.classrooms = classrooms;
      },
      (error) => {
        console.error('Error loading classrooms:', error);
      }
    );
  }
  // Router
  goToAdminClassroom() {
    this.router.navigate(["/admin-panel/classrooms"])
  }
  goToAdminStudent() {
    this.router.navigate(['/admin-panel/students']);
  }
  logout() {
    this.router.navigate(['/']);
  }
}
