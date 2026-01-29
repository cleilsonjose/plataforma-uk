import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// Interface para garantir que os dados sigam o padrão do seu Java
export interface RegisterRequest {
  nome: string;
  email: string;
  senha: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Ajuste esta URL se o seu Java estiver em outra porta
  private readonly apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  /**
   * Envia os dados para criar a conta no Backend
   */
  register(data: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  /**
   * Realiza a autenticação
   */
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
}