package com.pokemon.angular_pokemon.repository;

import com.pokemon.angular_pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Essa interface gerencia o acesso ao banco de dados utilizando Spring Data JPA.
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
