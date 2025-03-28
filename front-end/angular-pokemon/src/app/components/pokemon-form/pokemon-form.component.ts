import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon, PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokemon-form',
  templateUrl: './pokemon-form.component.html',
  styleUrls: ['./pokemon-form.component.css'],
})
export class PokemonFormComponent {
  pokemon: Pokemon = {
    nome: '',
    tipo: '',
    nivel: 1,
    imagemUrl: '',
  };

  constructor(
    private pokemonService: PokemonService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  capturarPokemon(): void {
    const treinadorId = Number(this.route.snapshot.paramMap.get('treinadorId'));
    const nomeTreinador = this.route.snapshot.paramMap.get('nomeTreinador');

    if (!treinadorId || !nomeTreinador) {
      alert('Treinador não encontrado');
      return;
    }

    this.pokemonService
      .capturarPokemon(treinadorId, nomeTreinador, this.pokemon)
      .subscribe(() => {
        this.router.navigate([
          'treinadores',
          treinadorId,
          nomeTreinador,
          'pokemons'
        ]);
      });
  }
}
