import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Pokemon, PokemonService } from 'src/app/services/pokemon/pokemon.service';

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

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id){
      this.pokemonService.buscarPorId(+id).subscribe((data) => {
        this.pokemon = data;
      });
    }
  }

  salvarEdicao(): void{
    this.pokemonService.atualizarPokemon(this.pokemon.id!, {
      nome: this.pokemon.nome,
      tipo: this.pokemon.tipo,
      imagemUrl: this.pokemon.imagemUrl,
    }).subscribe(() => {
      this.router.navigate(['/pokemons'])
    })
  }

}
