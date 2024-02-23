import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from 'src/app/model/classroom.model';
import { Result } from 'src/app/model/result.model';
import { Student } from 'src/app/model/student.model';
import { AdminService } from 'src/app/services/admin/admin.service';
declare let alertify: any;
declare let bootstrap: any;

@Component({
  selector: 'app-students',
  templateUrl: './admin-students.component.html',
  styleUrls: ['./admin-students.component.scss']
})
export class StudentsComponent implements OnInit{
  activeContent = '';
  students: Student[]= [];
  updatedStudentName = '';
  updatedStudentNumber = '';
  updatedStudentPassword = '';
  updatedStudentClassroom: Classroom | undefined = undefined;
  newStudentName = '';
  newStudentNumber = '';
  newStudentPassword = '';
  selectedStudentClassroom: Classroom | undefined = undefined;
  selectedStudent: Student | null = null;

  classrooms: Classroom[] = []
  results: Result[] = []

  
  createStudentModal: any;
  editStudentModal: any;
  studentResultsModal: any;

  constructor(
    private adminService: AdminService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.loadStudents();
    this.loadClassrooms();
    this.loadResults();
    this.editStudentModal = new bootstrap.Modal(document.getElementById('editStudentModal'));
    this.createStudentModal = new bootstrap.Modal(document.getElementById('createStudentModal'));
    this.studentResultsModal = new bootstrap.Modal(document.getElementById('studentResultsModal'));
  }

  loadClassrooms(): void {
    this.adminService.getClassrooms().subscribe(
      (classrooms) => {
        this.classrooms = classrooms;
      },
      (error) => {
        console.error('Error loading classrooms: ', error);
      }
    );
  }
  loadResults(): void {
    this.adminService.getAllResults().subscribe(
      (results) => {
        this.results = results
      },
      (error) => {
        console.error('Error loading results: ', error)
      }
    );
  }
  getStudentsResults(student: Student) {
    if (!student || !student.id) {
      return []; // Öğrenci yoksa veya öğrencinin kimliği yoksa boş bir dizi döndürün.
    }
  
    // Bu öğrencinin sınav sonuçlarını filtreleyin.
    return this.results.filter(result => result.student.id === student.id);
  }
  // Student CRUD
  loadStudents(): void {
    this.adminService.getAllStudents().subscribe(
      (students) => {
        this.students = students;
      },
      (error) => {
        console.error('Error loading students:', error);
      }
    );
  }
  createStudent(): void {
    if (this.newStudentName.trim() !== '' && this.newStudentNumber.trim() !== '' && this.newStudentPassword.trim() !== '' && this.selectedStudentClassroom) {
      const newStudent: Student = {
        name: this.newStudentName,
        number: this.newStudentNumber,
        password: this.newStudentPassword,
        classroom: this.selectedStudentClassroom
      };
  
      this.adminService.createStudent(newStudent).subscribe(
        (createdStudent) => {
          alertify.success('Student created: ' + createdStudent.name);
          this.loadStudents();
          this.createStudentModal.hide();
        },
        (error) => {
          alertify.error('Error creating student: ' + error);
        }
      );
    } else {
      alertify.error('Please enter valid student information.');
    }
  }
  selectStudentClassroom(classroom: Classroom): void {this.selectedStudentClassroom = classroom;}     
  selectClassroomForStudent(classroom: Classroom): void {this.updatedStudentClassroom = classroom;}
  updateStudent(): void {
    if (this.selectedStudent && this.updatedStudentClassroom && this.updatedStudentName !== "" && this.updatedStudentNumber !== "" && this.updatedStudentPassword !== "") {
      const updatedStudent: Student = {
        ...this.selectedStudent,
        name: this.updatedStudentName,
        number: this.updatedStudentNumber,
        password: this.updatedStudentPassword,
        classroom: this.updatedStudentClassroom
      };

      if (updatedStudent.name !== '' ) {
        this.adminService.updateStudent(updatedStudent).subscribe(
          () => {
            this.loadStudents();
            alertify.success('Student updated successfully.');
            this.editStudentModal.hide();
          },
          (error) => {
            alertify.error(
              'Error updating student. Please try again later.' + error
            );
          }
        );
      } else {
        alertify.error('Student must have a name.');
      }
    }
  }
  deleteStudentWithRelatedEntities(student: Student) {
    alertify.confirm('Are you sure to delete this student with its all related entities?', () => {
      this.adminService.deleteStudentWithRelatedEntities(student).subscribe(
          () => {
            alertify.success('Student with related entities deleted successfully!');
            this.loadStudents();
          },
          (error) => {
            alertify.error('Error deleting student with related entities!');
          }
        );
    })
    .set({ title: 'Delete Student' })
    .set({ labels: { ok: 'Delete', cancel: 'Cancel' } });
  }
  // Student Modal
  openCreateStudentModal(): void {
    this.newStudentName = '';
    this.newStudentNumber = '';
    this.newStudentPassword = '';
    this.selectedStudentClassroom;
    this.createStudentModal.show();
  }
  openEditStudentModal(student: Student): void {
    this.selectedStudent = student;
    this.updatedStudentName = student.name;
    this.updatedStudentNumber = student.number;
    this.updatedStudentPassword = student.password;
    this.updatedStudentClassroom = student.classroom;
    this.editStudentModal.show();
  }
  openStudentResultsModal(student: Student): void {
    this.selectedStudent = student;
    this.studentResultsModal.show();
}
  // Routers
  goToAdminClassroom() {
    this.router.navigate(['/admin-panel/classrooms'])
  }
  goToExamClassroom() {
    this.router.navigate(["/admin-panel/exams"])
  }
  logout() {
    this.router.navigate(['/']);
  }

}
