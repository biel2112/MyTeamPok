package com.pokemon.angular_pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemon.angular_pokemon.model.Treinador;

public interface TreinadorRepository extends JpaRepository<Treinador, Long>{
    
}
