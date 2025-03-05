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

  private apiUrl = 'http://localhost:8080/pokemons';



  constructor(private http: HttpClient) { }

  listarPokemons(): Observable<Pokemon[]> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(response => response.content) // Pegando apenas a lista dentro de "content"
    );
  }

  buscarPorId(id: number): Observable<Pokemon>{
    return this.http.get<Pokemon>(`${this.apiUrl}/${id}`);
  }

  cadastrarPokemon(pokemon: Pokemon): Observable<Pokemon>{
    return this.http.post<Pokemon>(this.apiUrl, pokemon);
  }

  atualizarPokemon(id: number, pokemon: Partial<Pokemon>): Observable<Pokemon>{
    return this.http.patch<Pokemon>(`${this.apiUrl}/${id}`, pokemon);
  }

  deletarPokemon(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  subirNivel(id: number): Observable<Pokemon> {
    return this.http.patch<Pokemon>(`${this.apiUrl}/${id}/subir-nivel`, {});
  }

  diminuirNivel(id: number): Observable<Pokemon> {
    return this.http.patch<Pokemon>(`${this.apiUrl}/${id}/diminuir-nivel`, {});
  }


}


