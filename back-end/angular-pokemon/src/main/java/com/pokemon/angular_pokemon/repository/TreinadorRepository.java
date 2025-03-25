package com.pokemon.angular_pokemon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.angular_pokemon.model.Treinador;

@Repository 
public interface TreinadorRepository extends JpaRepository<Treinador, Long>{

    Optional<Treinador> findByNome(String nome);
    
} 
