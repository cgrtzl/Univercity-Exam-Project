import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentLoginComponent } from './logins/student-login/student-login.component';
import { AdminLoginComponent } from './logins/admin-login/admin-login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { StudentsComponent } from './panels/admin/students/admin-students.component';
import { ClassroomsComponent } from './panels/admin/classrooms/admin-classrooms.component';
import { ExamsComponent } from './panels/admin/exams/admin-exams.component';
import { ProfileComponent } from './panels/student/profile/student-profile.component';
import { StudentExamsComponent } from './panels/student/exams/student-exams.component';

const routes: Routes = [
  { path: 'student-login', component: StudentLoginComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  { path: '', component: WelcomeComponent},
  { path: 'admin-panel/students', component: StudentsComponent },
  { path: 'admin-panel/classrooms', component: ClassroomsComponent },
  { path: 'admin-panel/exams', component: ExamsComponent },
  { path: 'student-panel/profile', component: ProfileComponent },
  { path: 'student-panel/exams', component: StudentExamsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
