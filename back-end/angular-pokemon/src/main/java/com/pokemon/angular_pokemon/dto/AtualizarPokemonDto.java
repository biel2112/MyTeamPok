package com.pokemon.angular_pokemon.dto;

// Recebe dados para a atualização de informações de algum pokemon
public record AtualizarPokemonDto (
    String nome,
    String tipo,
    Integer nivel,
    String imagemUrl
){}
