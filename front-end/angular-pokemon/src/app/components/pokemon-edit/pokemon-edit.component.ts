import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon, PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokemon-edit',
  templateUrl: './pokemon-edit.component.html',
  styleUrls: ['./pokemon-edit.component.css']
})
export class PokemonEditComponent implements OnInit {
  pokemon: Pokemon = {
    id: 0,
    nome: '',
    tipo: '',
    nivel: 0,
    imagemUrl: ''
  };

  treinadorId!: number;
  nomeTreinador!: string;
  pokemonId!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private pokemonService: PokemonService
  ) {}

  ngOnInit(): void {
    this.treinadorId = Number(this.route.snapshot.paramMap.get('treinadorId'));
    this.nomeTreinador = this.route.snapshot.paramMap.get('nomeTreinador') || '';

    const pokemonIdParam = this.route.snapshot.paramMap.get('pokemonId');
    console.log('pokemonIdParam da URL:', pokemonIdParam);

    if (!pokemonIdParam) {
      console.error('Erro: ID do Pokémon não encontrado na rota.');
      return;
    }

    this.pokemonId = Number(pokemonIdParam);
    console.log('pokemonId convertido:', this.pokemonId);

    if (isNaN(this.pokemonId) || this.pokemonId <= 0) {
      console.error('Erro: ID do Pokémon inválido.');
      return;
    }

    this.carregarPokemon();
  }

  carregarPokemon(): void {
    this.pokemonService.getPokemonById(this.treinadorId, this.pokemonId).subscribe({
      next: (pokemon) => {
        console.log('Pokémon recebido:', pokemon);
        this.pokemon = pokemon;
      },
      error: (error) => {
        console.error('Erro ao buscar Pokémon:', error);
      }
    });
  }

  salvarEdicao(): void {
    if (!this.pokemon.id || isNaN(this.pokemon.id) || this.pokemon.id <= 0) {
      alert('Erro: ID do Pokémon inválido.');
      return;
    }

    this.pokemonService.atualizarPokemon(
      this.treinadorId,
      this.nomeTreinador,
      this.pokemon.id,
      {
        nome: this.pokemon.nome,
        tipo: this.pokemon.tipo,
        imagemUrl: this.pokemon.imagemUrl
      }
    ).subscribe({
      next: () => {
        this.router.navigate(['/treinadores', this.treinadorId, this.nomeTreinador, 'pokemons']);
      },
      error: (error) => {
        console.error('Erro ao atualizar Pokémon:', error);
      }
    });
  }
}
