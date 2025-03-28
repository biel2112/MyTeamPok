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

}


