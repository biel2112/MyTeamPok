import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Treinador, TreinadorService } from 'src/app/services/treinador/treinador.service';

@Component({
  selector: 'app-cadastro-treinador',
  templateUrl: './cadastro-treinador.component.html',
  styleUrls: ['./cadastro-treinador.component.css']
})
export class CadastroTreinadorComponent {

  nome: string = '';
  senha: string = '';

  constructor(private treinadorService: TreinadorService, private router: Router) {}

  cadastrarTreinador() {
    const novoTreinador: Treinador = {
      nome: this.nome,
      senha: this.senha,
      timePrincipal: [],
      computador: []
    };

    this.treinadorService.cadastrarTreinador(novoTreinador).subscribe(
      (resposta) => {
        alert('Treinador cadastrado com sucesso!');
        this.router.navigate([`/treinadores/${this.nome}/home`]); // Redireciona para a tela de login
      },
      (erro) => {
        console.error('Erro ao cadastrar treinador:', erro);
        alert('Erro ao conectar ao servidor.');
      }
    );
  }
}
