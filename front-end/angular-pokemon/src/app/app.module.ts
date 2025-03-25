import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PokemonListComponent } from './components/pokemons/pokemon-list/pokemon-list.component';
import { PokemonFormComponent } from './components/pokemons/pokemon-form/pokemon-form.component';
import { PokemonEditComponent } from './components/pokemons/pokemon-edit/pokemon-edit.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    PokemonListComponent,
    PokemonFormComponent,
    PokemonEditComponent
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
