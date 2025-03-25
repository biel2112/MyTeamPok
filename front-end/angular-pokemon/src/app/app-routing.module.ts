import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokemonListComponent } from './components/pokemons/pokemon-list/pokemon-list.component';
import { PokemonFormComponent } from './components/pokemons/pokemon-form/pokemon-form.component';
import { PokemonEditComponent } from './components/pokemons/pokemon-edit/pokemon-edit.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/pokemons',
    pathMatch: 'full',
  },
  {
    path: 'pokemons',
    component: PokemonListComponent,
  },
  {
    path: 'pokemons/new',
    component: PokemonFormComponent,
  },
  {
    path: 'pokemons/edit/:id',
    component: PokemonEditComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
