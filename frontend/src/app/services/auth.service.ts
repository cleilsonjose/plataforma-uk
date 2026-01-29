import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Mantemos apenas a URL que aponta para o seu novo Controller profissional
  private readonly apiUrl = 'http://localhost:8080/api/acompanhantes';

  constructor(private http: HttpClient) { }

  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  login(credentials: any): Observable<any> {
    // Esse será nosso próximo passo no Java
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
}