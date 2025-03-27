package com.pokemon.angular_pokemon.dto;

import java.util.List;

import com.pokemon.angular_pokemon.model.Pokemon;

public record TreinadorDto(String nome, List<Pokemon> pokemons) {
} 
