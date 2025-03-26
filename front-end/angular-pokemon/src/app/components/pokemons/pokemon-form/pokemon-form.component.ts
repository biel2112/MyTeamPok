import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Pokemon, PokemonService } from 'src/app/services/pokemon/pokemon.service';

@Component({
  selector: 'app-pokemon-form',
  templateUrl: './pokemon-form.component.html',
  styleUrls: ['./pokemon-form.component.css']
})
export class PokemonFormComponent {

  pokemon: Pokemon = {
    nome:'',
    tipo:'',
    nivel:1,
    imagemUrl:''
  };

  constructor(private pokemonService: PokemonService, private router: Router){}

  cadastrarPokemon(): void{
    this.pokemonService.cadastrarPokemon(this.pokemon).subscribe(() => {
      this.router.navigate(['/']);
    })
  }
}
