import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { Student } from 'src/app/model/student.model';
import { StudentService } from 'src/app/services/student/student.service';
declare let alertify: any;

@Component({
  selector: 'app-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.scss']
})
export class ProfileComponent implements OnInit {
  currentUser: Student | any;
  activeContent = '';
  newPassword = '';
  confirmNewPassword = '';
  currentPassword = '';

  constructor(
    private studentService: StudentService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
  }
  
  updatePassword() {
    if (this.newPassword !== this.confirmNewPassword) {
      alertify.warning("New password and confirm password don't match.");
      return;
    }

    if (this.currentUser.password !== this.currentPassword) {
      alertify.warning('Current password is incorrect.');
      return;
    }

    this.studentService.updatePassword(this.currentUser.id, this.newPassword).subscribe((response) => {
      if (response['message'] === 'Password updated successfully.') {
        alertify.success('Password changed successfully.');
        this.currentPassword = '';
        this.newPassword = '';
        this.confirmNewPassword = '';
      } else {
        alertify.error('Password update failed.');
      }
    });
  }
  //
  goToStudentExam(){this.router.navigate(['/student-panel/exams'])}
  logout() {this.router.navigate(['/']);}
}
