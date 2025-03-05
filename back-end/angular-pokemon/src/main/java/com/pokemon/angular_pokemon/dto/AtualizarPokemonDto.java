package com.pokemon.angular_pokemon.dto;

public record AtualizarPokemonDto (
    String nome,
    String tipo,
    Integer nivel,
    String imagemUrl
){}
