import { Component, OnInit } from '@angular/core';
import { faArrowDown, faArrowUp, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Pokemon, PokemonService } from 'src/app/services/pokemon/pokemon.service';

@Component({
  selector: 'app-pokemon-list',
  templateUrl: './pokemon-list.component.html',
  styleUrls: ['./pokemon-list.component.css']
})
export class PokemonListComponent implements OnInit{

  pokemons: Pokemon[] = [];

  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faEdit = faEdit;
  faTrash = faTrash;

  constructor(private pokemonService: PokemonService){}

  ngOnInit(): void {
    this.pokemonService.listarPokemons().subscribe(
      (pokemons) => {
        this.pokemons = pokemons;
      },
      (error) => {
        console.error('Erro ao carregar os Pokémons:', error);
      }
    );
  }

  carregarPokemon(): void{
    this.pokemonService.listarPokemons().subscribe((data) => {
      this.pokemons = data;
    })
  }

  excluirPokemon(id: number): void{
    this.pokemonService.deletarPokemon(id).subscribe(() => {
      this.carregarPokemon();
    })
  }

  aumentarNivel(id: number): void{
    this.pokemonService.subirNivel(id).subscribe(() => {
      this.carregarPokemon();
    })
  }

  diminuirNivel(id: number): void{
    this.pokemonService.diminuirNivel(id).subscribe(() => {
      this.carregarPokemon();
    })
  }

  atualizarPokemon(id: number): void{
    console.log("Atualizando Pokemon")
  }

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
      Terra: 'bg-terra text-dark'
    };

    return cores[tipo] || 'bg-light text-dark'; // Padrão caso o tipo não esteja na lista
  }
}
