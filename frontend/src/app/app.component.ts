import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div style="text-align:center; margin-top: 50px;">
      <h1>Bem-vindo à Plataforma UK</h1>
      <p>Status da conexão com o Backend:</p>
      <h2 style="color: green;">{{ mensagem }}</h2>
    </div>
  `
})
export class AppComponent implements OnInit {
  mensagem = 'Tentando conectar ao Java...';

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.getHello().subscribe({
      next: (dados) => this.mensagem = dados.mensagem,
      error: (err) => this.mensagem = 'Erro ao conectar! Verifique se o Backend está rodando.'
    });
  }
}