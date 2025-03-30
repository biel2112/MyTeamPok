import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon, PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-pokemon-edit',
  templateUrl: './pokemon-edit.component.html',
  styleUrls: ['./pokemon-edit.component.css']
})
export class PokemonEditComponent implements OnInit{

  pokemon: Pokemon = {
    id: 0,
    nome: '',
    tipo: '',
    nivel: 0,
    imagemUrl: ''
  };

  constructor(private route: ActivatedRoute, private router: Router, private pokemonService: PokemonService){}

  treinadorId!: number;
  nomeTreinador!: string;

  ngOnInit(): void {
    const treinadorId = Number(this.route.snapshot.paramMap.get('treinadorId'));
    const nomeTreinador = this.route.snapshot.paramMap.get('nomeTreinador') || '';



    const pokemonIdParam = this.route.snapshot.paramMap.get('pokemonId');
    console.log("pokemonIdParam da URL:", pokemonIdParam); // <-- Verifica o que chega da rota




    if (!pokemonIdParam) {
      console.error("Erro: ID do Pokémon não encontrado na rota.");
      return;
    }

    const pokemonId = Number(pokemonIdParam);
    console.log("pokemonId convertido:", pokemonId); // <-- Verifica se a conversão foi bem-sucedida

    if (isNaN(pokemonId) || pokemonId <= 0) {
      console.error("Erro: ID do Pokémon inválido.");
      return;
    }

    console.log(`URL: http://localhost:8080/treinadores/${treinadorId}/${nomeTreinador}/pokemons/${pokemonId}`);


    this.pokemonService.getPokemonById(this.treinadorId, this.nomeTreinador, pokemonId).subscribe((pokemon) => {
      console.log("Pokémon recebido:", pokemon);
      this.pokemon = pokemon;
    }, error => {
      console.error("Erro ao buscar Pokémon:", error);
    });
  }

  salvarEdicao(): void{
    if (!this.pokemon.id || isNaN(this.pokemon.id) || this.pokemon.id <= 0) {
      alert("Erro: ID do Pokémon inválido.");
      return;
    }

    this.pokemonService.atualizarPokemon(
      this.treinadorId,
      this.nomeTreinador,
      this.pokemon.id,
      {
        nome: this.pokemon.nome,
        tipo: this.pokemon.tipo,
        imagemUrl: this.pokemon.imagemUrl,
      }
    ).subscribe(() => {
      this.router.navigate(['/treinadores', this.treinadorId, this.nomeTreinador, 'pokemons']);
    }, error => {
      console.error("Erro ao atualizar Pokémon:", error);
    });
  }
}
