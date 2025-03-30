import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokemonListComponent } from './components/pokemon-list/pokemon-list.component';
import { PokemonFormComponent } from './components/pokemon-form/pokemon-form.component';
import { DeletePokemonComponent } from './components/delete-pokemon/delete-pokemon.component';
import { PokemonEditComponent } from './components/pokemon-edit/pokemon-edit.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/treinadores',
    pathMatch: 'full',
  },
  {
    path: 'treinadores/:treinadorId/:nomeTreinador/pokemons',
    component: PokemonListComponent
  },
  {
    path: 'treinadores/:treinadorId/:nomeTreinador/pokemons/new',
    component: PokemonFormComponent,
  },
  {
    path: 'treinadores/:treinadorId/:nomeTreinador/pokemons/delete/:pokemonId',
    component: DeletePokemonComponent,
  },
  {
    path: 'treinadores/:treinadorId/:nomeTreinador/pokemons/edit/:pokemonId',
    component: PokemonEditComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
