import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

export interface Pokemon{
  id?: number;
  nome: string;
  tipo: string;
  nivel: number;
  imagemUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class PokemonService {


  private apiUrl = 'http://localhost:8080/treinadores';

  constructor(private http: HttpClient) { }

  listarPokemons(treinadorId: number, nomeTreinador: string): Observable<any[]> {
    const url = `${this.apiUrl}/${treinadorId}/${nomeTreinador}/pokemons`;
    return this.http.get<any[]>(url);
  }

  getPokemonById(treinadorId: number, id: number): Observable<Pokemon> {
    return this.http.get<Pokemon>(`${this.apiUrl}/${treinadorId}/pokemons/${id}`);
  }

  capturarPokemon(treinadorId: number, nomeTreinador: string, pokemon: Pokemon): Observable<Pokemon>{
    const url = `${this.apiUrl}/${treinadorId}/${nomeTreinador}/pokemons/new`;
    return this.http.post<Pokemon>(url, pokemon);
  }

  atualizarPokemon(treinadorId: Number, nomeTreinador: String, id: number, pokemon: Partial<Pokemon>): Observable<Pokemon>{
    return this.http.patch<Pokemon>(`${this.apiUrl}/${treinadorId}/${nomeTreinador}/pokemons/edit/${id}`, pokemon);
  }

  deletarPokemon(treinadorId: number, nomeTreinador: string, id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${treinadorId}/${nomeTreinador}/pokemons/delete/${id}`);
  }

  subirNivel(id: number): Observable<Pokemon> {
    return this.http.patch<Pokemon>(`${this.apiUrl}/${id}/subir-nivel`, {});
  }

  diminuirNivel(id: number): Observable<Pokemon> {
    return this.http.patch<Pokemon>(`${this.apiUrl}/${id}/diminuir-nivel`, {});
  }


}


