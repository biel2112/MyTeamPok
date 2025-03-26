
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { faArrowDown, faArrowUp, faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { Pokemon, PokemonService } from 'src/app/services/pokemon/pokemon.service';
import { TreinadorService } from 'src/app/services/treinador/treinador.service';

@Component({
  selector: 'app-treinador-home',
  templateUrl: './treinador-home.component.html',
  styleUrls: ['./treinador-home.component.css']
})
export class TreinadorHomeComponent {
  pokemons: Pokemon[] = [];
  nomeTreinador: string = '';
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faEdit = faEdit;
  faTrash = faTrash;

  constructor(
    private treinadorService: TreinadorService,
    private pokemonService: PokemonService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.nomeTreinador = this.route.snapshot.paramMap.get('nome') || 'Treinador';
    this.carregarPokemonsDoTimePrincipal();
  }

  carregarPokemonsDoTimePrincipal(): void {
    this.treinadorService.getPokemonsDoTimePrincipal(this.nomeTreinador).subscribe(
      (pokemons) => {
        this.pokemons = pokemons;
      },
      (error) => {
        console.error('Erro ao carregar os Pokémons do time principal:', error);
      }
    );
  }

    excluirPokemon(id: number): void{
      this.pokemonService.deletarPokemon(id).subscribe(() => {
        this.carregarPokemonsDoTimePrincipal();
      })
    }

    aumentarNivel(id: number): void{
      this.pokemonService.subirNivel(id).subscribe(() => {
        this.carregarPokemonsDoTimePrincipal();
      })
    }

    diminuirNivel(id: number): void{
      this.pokemonService.diminuirNivel(id).subscribe(() => {
        this.carregarPokemonsDoTimePrincipal();
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
