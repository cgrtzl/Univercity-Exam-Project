import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExamService {
  constructor(private http: HttpClient) {}

  isExamActive(examId: string): Observable<boolean> {
    const url = `http://localhost:8080/exams/${examId}/isActive`;
    return this.http.get<boolean>(url);
  }

}
