import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Classroom } from 'src/app/model/classroom.model';
import { Student } from 'src/app/model/student.model';
import { AdminService } from 'src/app/services/admin/admin.service';
declare let alertify: any;
declare let bootstrap: any;

@Component({
  selector: 'app-classrooms',
  templateUrl: './admin-classrooms.component.html',
  styleUrls: ['./admin-classrooms.component.scss']
})
export class ClassroomsComponent implements OnInit{
  activeContent = '';
  classrooms: Classroom[] = [];
  updatedClassName = '';
  selectedClassroom: Classroom | null = null;
  newClassName = '';
  createdClassroom: Classroom | undefined;

  students: Student[] = [];
  studentsInSelectedClassroom: Student[] = [];

  studentListModal: any;
  editClassroomModal: any;
  createClassroomModal: any;
  
  constructor(
    private adminService: AdminService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadClassrooms();
    this.loadStudents();
    this.editClassroomModal = new bootstrap.Modal(document.getElementById('editClassroomModal'));
    this.createClassroomModal = new bootstrap.Modal(document.getElementById('createClassroomModal'));
    this.studentListModal = new bootstrap.Modal(document.getElementById('studentListModal'));
  }

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
  // Classroom CRUD
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
  createClassroom(newClassName: string): void {
    if (newClassName.trim() !== '') {
      const newClassroom = {
        name: this.newClassName
      };

      this.adminService.createClassroom(newClassroom).subscribe(
        (createdClassroom) => {
          alertify.success('Classroom created: ' + createdClassroom.name);
          this.loadClassrooms();
          this.createClassroomModal.hide();
        },
        (error) => {
          alertify.error('Error creating classroom: ' + error);
        }
      );
    } else {
      alertify.error('Please enter a valid name.');
    }
  }
  updateClassroom() {
    if (this.selectedClassroom) {
      const updatedClassroom: Classroom = {
        ...this.selectedClassroom,
        name: this.updatedClassName,
      };

      if (updatedClassroom.name !== '') {
        this.adminService.updateClassroom(updatedClassroom).subscribe(
          () => {
            this.loadClassrooms();
            alertify.success('Classroom updated successfully.');
            this.editClassroomModal.hide();
          },
          (error) => {
            alertify.error(
              'Error updating classroom. Please try again later.' + error
            );
          }
        );
      } else {
        alertify.error('Classroom must have a name.');
      }
    }
  }
  deleteClassroom(classroom: Classroom): void {
    alertify.confirm('Are you sure to delete this classroom with its all related entities?', () => {
      this.adminService.deleteClassroom(classroom).subscribe(
          () => {
            alertify.success('Classroom with related entities deleted successfully!');
            this.loadClassrooms();
          },
          (error) => {
            alertify.error('Error deleting classroom with related entities!');
          }
        );
    })
    .set({ title: 'Classroom Exam' })
    .set({ labels: { ok: 'Delete', cancel: 'Cancel' } });
  }
  getStudentCount(classroom: Classroom): number {
    return this.students.filter(student => student.classroom.id === classroom.id).length;
  }
  getStudentsByClassroom(classroom: Classroom): Student[] {
    return this.students.filter(student => student.classroom.id === classroom.id);
  }
  // Classroom Modal
  openStudentListModal(classroom: Classroom): void {
    this.studentsInSelectedClassroom = this.getStudentsByClassroom(classroom);
    this.selectedClassroom = classroom;
    this.studentListModal.show();
  }
  openEditClassroomModal(classroom: Classroom): void {
    this.updatedClassName = classroom.name;
    this.selectedClassroom = classroom;
    this.editClassroomModal.show();
  }
  openCreateClassroomModal() {
    this.newClassName = '';
    this.createClassroomModal.show();
  }

  // Router
  goToAdminStudent() {
    this.router.navigate(['/admin-panel/students']);
  }
  goToAdminExam() {
    this.router.navigate(["/admin-panel/exams"])
  }
  logout() {
    this.router.navigate(['/']);
  }
}
