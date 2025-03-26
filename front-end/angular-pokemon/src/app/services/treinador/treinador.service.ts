import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pokemon } from '../pokemon/pokemon.service';

export interface Treinador {
  id?: number;
  nome: string;
  senha: string;
  timePrincipal: Pokemon[];
  computador: Pokemon[]
}


@Injectable({
  providedIn: 'root'
})
export class TreinadorService {

  private apiUrl = 'http://localhost:8080/treinadores';

  constructor(private http: HttpClient) { }

  cadastrarTreinador(treinador: Treinador): Observable<Treinador> {
    return this.http.post<Treinador>(`${this.apiUrl}/cadastro`, treinador);
  }

  login(nome: string, senha: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { nome, senha });
  }

  getPokemonsDoTimePrincipal(treinadorNome: string): Observable<Pokemon[]> {
    return this.http.get<Pokemon[]>(`${this.apiUrl}/${treinadorNome}/home`);
  }
}
