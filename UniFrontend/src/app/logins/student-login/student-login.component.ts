import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { Student } from 'src/app/model/student.model';

declare let alertify: any;

@Component({
  selector: 'app-student-login',
  templateUrl: './student-login.component.html',
  styleUrls: ['./student-login.component.scss'],
})
export class StudentLoginComponent implements OnInit {
  loginForm!: FormGroup;
  currentUser: Student | undefined;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      number: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }

  onSubmit(): void {
    const number = this.loginForm.value.number;
    const password = this.loginForm.value.password;
    if (this.loginForm.valid) {
    this.authService.studentLogin(number, password).subscribe(
      (student) => {
        this.currentUser = student;
        this.router.navigate(['/student-panel/profile'])
        alertify.success("Welcome!")
      },
      (error) => {
        if (error.status === 401) {
          alertify.error('Invalid credentials. Please check your username and password.');
        } else {
          console.error(error);
          alertify.error('An error occurred while processing your request. Please try again later.');
        }
      }
    );
    } else {
      alertify.warning('Please fill in all required fields.');
    }
  }
  
}
