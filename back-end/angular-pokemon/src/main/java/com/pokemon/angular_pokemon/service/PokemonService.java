package com.pokemon.angular_pokemon.service;

import com.pokemon.angular_pokemon.dto.AtualizarPokemonDto;
import com.pokemon.angular_pokemon.dto.PokemonDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.repository.PokemonRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

// Essa classe contém toda a lógica de negócio do sistema.
@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository repository){
        this.pokemonRepository = repository;
    }

    public Pokemon buscarPorId(Long id){
        return pokemonRepository.findById(id).orElseThrow(() -> new RuntimeException("Pokemon não encontrado!")) ;
    }

    // Exclui um Pokemon pelo ID
    public void deletarPokemon(Long id){
        pokemonRepository.deleteById(id);
    }

    // Aumenta o nível do Pokemon
    public Pokemon subirNivel(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));
        pokemon.setNivel(pokemon.getNivel() + 1);
        return pokemonRepository.save(pokemon);
    }

    // Diminui o nível do Pokemon
    public Pokemon diminuirNivel(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));
        if (pokemon.getNivel() > 1) {
            pokemon.setNivel(pokemon.getNivel() - 1);
        }
        return pokemonRepository.save(pokemon);
    }


}
