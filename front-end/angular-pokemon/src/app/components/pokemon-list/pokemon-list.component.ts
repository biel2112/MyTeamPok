import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  faArrowDown,
  faArrowUp,
  faEdit,
  faTrash,
} from '@fortawesome/free-solid-svg-icons';
import { Pokemon, PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokemon-list',
  templateUrl: './pokemon-list.component.html',
  styleUrls: ['./pokemon-list.component.css'],
})
export class PokemonListComponent implements OnInit {
  pokemons: Pokemon[] = [];
  treinadorId: number | null = null;
  nomeTreinador: string | null = null;

  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faEdit = faEdit;
  faTrash = faTrash;

  constructor(
    private router: Router,
    private pokemonService: PokemonService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Pegando os parâmetros da URL
    this.treinadorId = Number(this.route.snapshot.paramMap.get('treinadorId'));
    this.nomeTreinador =
      this.route.snapshot.paramMap.get('nomeTreinador') || '';

    // Buscando os Pokémons do treinador
    this.pokemonService
      .listarPokemons(this.treinadorId, this.nomeTreinador)
      .subscribe((data: Pokemon[]) => {
        this.pokemons = data;
      });
  }

  // Esse método pode ser utilizado se você precisar recarregar a lista de pokémons
  carregarPokemon(): void {
    this.pokemonService
      .listarPokemons(this.treinadorId!, this.nomeTreinador!)
      .subscribe((data: Pokemon[]) => {
        this.pokemons = data;
      });
  }

  navegarParaCapturar() {
    const treinadorId = this.route.snapshot.paramMap.get('treinadorId');
    const nomeTreinador = this.route.snapshot.paramMap.get('nomeTreinador');

    if (treinadorId && nomeTreinador) {
      this.router.navigate([
        'treinadores',
        treinadorId,
        nomeTreinador,
        'pokemons',
        'new'
      ]);
    }
  }

  // Adicionando método para obter a classe de cores dos cards baseado no tipo de Pokémon
  coresCards(tipo: string): string {
    const cores: { [key: string]: string } = {
      Fogo: 'bg-fogo text-white',
      Água: 'bg-agua text-white',
      Planta: 'bg-planta text-white',
      Elétrico: 'bg-eletrico text-dark',
      Psíquico: 'bg-psiquico text-white',
      Gelo: 'bg-gelo text-dark',
      Pedra: 'bg-pedra text-white',
      Normal: 'bg-light text-dark',
      Fantasma: 'bg-fantasma text-white',
      Dragão: 'bg-dragao text-white',
      Lutador: 'bg-lutador text-white',
      Venenoso: 'bg-venenoso text-white',
      Inseto: 'bg-inseto text-dark',
      Aço: 'bg-aco text-dark',
      Fada: 'bg-fada text-light',
      Terra: 'bg-terra text-dark',
    };

    return cores[tipo] || 'bg-light text-dark'; // Padrão caso o tipo não esteja na lista
  }
}
