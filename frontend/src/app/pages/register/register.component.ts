import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core'; // Adicionado
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

/** Classe para forçar a exibição do erro de senhas diferentes no campo */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidCtrl = !!(control && control.invalid && control.touched);
    const invalidParent = !!(control && control.parent && control.parent.hasError('passwordMismatch') && control.touched);
    return (invalidCtrl || invalidParent);
  }
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule, MatCardModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatIconModule, RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  hideSenha = true;
  hideConfirmar = true;
  matcher = new MyErrorStateMatcher(); // Instância do matcher para o HTML

  constructor(private fb: FormBuilder, private authService: AuthService,  private router: Router) {
    this.registerForm = this.fb.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(/^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])/)
      ]],
      confirmarSenha: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const senha = control.get('senha');
    const confirmar = control.get('confirmarSenha');
    return senha && confirmar && senha.value !== confirmar.value ? { passwordMismatch: true } : null;
  }

  registrar() {
    if (this.registerForm.valid) {
      // Pegamos os dados do formulário
      const dados = this.registerForm.value;

      this.authService.register(dados).subscribe({
        next: (res) => {
          console.log('Success! Registered in the database:', res);
          alert('Account created successfully!');
          this.router.navigate(['/welcome']);//<= adicionei aqui
        },
        error: (err) => {
          console.error('Registration failed:', err);
          alert('Error connecting to the server. Check the console.');
        }
      }); 
    }
  }
}