import { TreinadorHomeComponent } from './components/treinadores/treinador-home/treinador-home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PokemonListComponent } from './components/pokemons/pokemon-list/pokemon-list.component';
import { PokemonFormComponent } from './components/pokemons/pokemon-form/pokemon-form.component';
import { PokemonEditComponent } from './components/pokemons/pokemon-edit/pokemon-edit.component';
import { LoginTreinadorComponent } from './components/treinadores/login-treinador/login-treinador.component';
import { CadastroTreinadorComponent } from './components/treinadores/cadastro-treinador/cadastro-treinador.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/treinadores/login',
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
  },
  {
    path: 'treinadores/login',
    component: LoginTreinadorComponent,
  },
  {
    path: 'treinadores/cadastro',
    component: CadastroTreinadorComponent,
  },
  {
    path: 'treinadores/:nome/home',
    component: TreinadorHomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
