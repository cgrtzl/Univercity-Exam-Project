import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/'; // Spring Boot API'nizin temel URL'si

  constructor(private http: HttpClient) {}

  studentLogin(number: string, password: string): Observable<any> {
    const body = { number, password };
    return this.http.post(`${this.baseUrl}student/login`, body)
  }

  adminLogin(name: string, password: string): Observable<any> {
    const body = { name, password };
    return this.http.post(`${this.baseUrl}admin/login`, body);
  }

}
