import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';
declare let alertify: any;

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {
  loginForm!: FormGroup;
  tokenName: string | undefined;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  goToHomePage() {
    this.router.navigate(['/']);
  }

  onSubmit(): void {
    const name = this.loginForm.value.name;
    const password = this.loginForm.value.password;
    if (this.loginForm.valid) {
    this.authService.adminLogin(name, password).subscribe(
      () => {
        this.router.navigate(['/admin-panel/classrooms'])
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
