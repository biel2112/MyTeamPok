package com.pokemon.angular_pokemon.dto;

import java.util.List;

public record TreinadorDto (
    String nome, String senha, List<PokemonDto> timePrincipal, List<PokemonDto> computador
){
    
}
