package com.pokemon.angular_pokemon.repository;

import com.pokemon.angular_pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
