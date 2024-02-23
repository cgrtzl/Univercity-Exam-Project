import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { StudentLoginComponent } from './logins/student-login/student-login.component';
import { AdminLoginComponent } from './logins/admin-login/admin-login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { StudentsComponent } from './panels/admin/students/admin-students.component';
import { ExamsComponent } from './panels/admin/exams/admin-exams.component';
import { ClassroomsComponent } from './panels/admin/classrooms/admin-classrooms.component';
import { ProfileComponent } from './panels/student/profile/student-profile.component';
import { StudentExamsComponent } from './panels/student/exams/student-exams.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    StudentLoginComponent,
    AdminLoginComponent,
    StudentsComponent,
    ExamsComponent,
    ClassroomsComponent,
    ProfileComponent,
    StudentExamsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
