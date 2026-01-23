import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet], // Importamos o RouterOutlet para as rotas funcionarem
  template: `
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}