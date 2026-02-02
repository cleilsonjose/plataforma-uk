import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink, Router } from '@angular/router'; // Adicionado Router
import { AuthService } from '../../services/auth.service'; // Adicionado import do seu Service

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

// ... imports permanecem os mesmos

export class LoginComponent {
  loginForm: FormGroup;
  hide = true;
  errorMessage: string = '';
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      // Lembre-se: 'senha' para casar com o seu Java Acompanhante.java
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  entrar() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          this.router.navigate(['/welcome']);
        },
        error: (err) => {
          // Em vez de alert(), alimentamos a variável
          this.errorMessage = 'Login failed! Check your email and password.';
          console.error('Login error:', err);
        }
      });
    }
  }

  // Lógica que faz a ponte com o Backend
  private executarLogin() {
    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        console.log('Login successful! Response from Java:', res);
        this.router.navigate(['/welcome']);
      },
      error: (err) => {
        console.error('Login error:', err);
        alert('Login failed! Check your email and password.');
      }
    });
  }
}

