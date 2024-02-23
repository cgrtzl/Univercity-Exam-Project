import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  

  constructor(private http: HttpClient) {}
  updatePassword(studentId: string, newPassword: string): Observable<any> {
    const url = `http://localhost:8080/student/${studentId}/update-password`;
    const requestBody = {
      newPassword: newPassword
    };
    return this.http.put(url, requestBody);
  }

  getAssignedExams(studentId: string): Observable<any> {
    const url = `http://localhost:8080/student/${studentId}/exams`;
    
    return this.http.get(url);
  }

}
