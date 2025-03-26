import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PokemonListComponent } from './components/pokemons/pokemon-list/pokemon-list.component';
import { PokemonFormComponent } from './components/pokemons/pokemon-form/pokemon-form.component';
import { PokemonEditComponent } from './components/pokemons/pokemon-edit/pokemon-edit.component';
import { FormsModule } from '@angular/forms';
import { CadastroTreinadorComponent } from './components/treinadores/cadastro-treinador/cadastro-treinador.component';
import { LoginTreinadorComponent } from './components/treinadores/login-treinador/login-treinador.component';
import { TreinadorHomeComponent } from './components/treinadores/treinador-home/treinador-home.component';


@NgModule({
  declarations: [
    AppComponent,
    PokemonListComponent,
    PokemonFormComponent,
    PokemonEditComponent,
    CadastroTreinadorComponent,
    LoginTreinadorComponent,
    TreinadorHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
