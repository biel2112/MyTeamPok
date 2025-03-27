package com.pokemon.angular_pokemon.dto;

// Recebe as informações para a criação de algum Pokemon.
public record PokemonDto (String nome, String tipo, Integer nivel, String imagemUrl, Long treinadorId){

}
