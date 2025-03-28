import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-delete-pokemon',
  templateUrl: './delete-pokemon.component.html',
  styleUrls: ['./delete-pokemon.component.css']
})
export class DeletePokemonComponent implements OnInit{

  treinadorId!: number;
  nomeTreinador!: string;
  pokemonId!: number;

  constructor(private route: ActivatedRoute, private router: Router, private pokemonService: PokemonService){

  }
  ngOnInit(): void {
    this.treinadorId = Number(this.route.snapshot.paramMap.get('treinadorId'));
    this.nomeTreinador = this.route.snapshot.paramMap.get('nomeTreinador')!;
    this.pokemonId = Number(this.route.snapshot.paramMap.get('pokemonId'));
  }

  deletarPokemon(): void{

    if(!this.treinadorId || !this.nomeTreinador){
      alert("Treinador não encontrado!")
    };


    this.pokemonService.deletarPokemon(this.treinadorId, this.nomeTreinador, this.pokemonId)
      .subscribe(() => {
        alert('Pokémon removido com sucesso!');
        this.router.navigate(['/treinadores', this.treinadorId, this.nomeTreinador, 'pokemons']);
      })
  }

  cancelar(): void {
    this.router.navigate(['/treinadores', this.treinadorId, this.nomeTreinador, 'pokemons']);
  }


}
