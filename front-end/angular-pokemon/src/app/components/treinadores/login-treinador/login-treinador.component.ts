import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { TreinadorService } from 'src/app/services/treinador/treinador.service';

@Component({
  selector: 'app-login-treinador',
  templateUrl: './login-treinador.component.html',
  styleUrls: ['./login-treinador.component.css']
})
export class LoginTreinadorComponent {

  nome: string = '';
  senha: string = '';

  constructor(private treinadorService: TreinadorService, private router: Router) {}

  fazerLogin(loginForm: NgForm) {
    if (loginForm.valid) {
      // Simula um login bem-sucedido, faça a verificação de nome e senha
      this.treinadorService.login(this.nome, this.senha).subscribe(
        (resposta) => {
          // Supondo que a resposta tenha um status de sucesso
          if (resposta.success) {
            // Redireciona para a página do treinador com o nome
            this.router.navigate([`/treinadores/${this.nome}/home`]);
          } else {
            alert('Login falhou. Verifique suas credenciais.');
          }
        },
        (erro) => {
          console.error('Erro ao tentar fazer login:', erro);
console.error('Status do erro:', erro.status);
console.error('Mensagem do erro:', erro.message);
console.error('Detalhes do erro:', erro.error);

          alert('Erro no login');
        }
      );
    }
  }

  irParaCadastro() {
    this.router.navigate(['/treinadores/cadastro']); // Redireciona para a tela de cadastro
  }

}
