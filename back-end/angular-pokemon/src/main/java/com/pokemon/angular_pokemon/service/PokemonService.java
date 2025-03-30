package com.pokemon.angular_pokemon.service;

import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.repository.PokemonRepository;
import com.pokemon.angular_pokemon.repository.TreinadorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Essa classe contém toda a lógica de negócio do sistema.
@Service
@RequiredArgsConstructor
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    @Autowired
    private TreinadorRepository treinadorRepository;

    // Aumenta o nível do Pokémon, validando o treinador
public Pokemon subirNivel(Long idTreinador, String nomeTreinador, Long idPokemon) {
    Treinador treinador = treinadorRepository.findById(idTreinador)
            .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

    if (!treinador.getNome().equals(nomeTreinador)) {
        throw new RuntimeException("Nome do treinador não corresponde ao ID");
    }

    Pokemon pokemon = pokemonRepository.findById(idPokemon)
            .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

    if (!pokemon.getTreinador().getId().equals(idTreinador)) {
        throw new RuntimeException("Este Pokémon não pertence ao treinador informado");
    }

    pokemon.setNivel(pokemon.getNivel() + 1);
    return pokemonRepository.save(pokemon);
}

// Diminui o nível do Pokémon, validando o treinador
public Pokemon diminuirNivel(Long idTreinador, String nomeTreinador, Long idPokemon) {
    Treinador treinador = treinadorRepository.findById(idTreinador)
            .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

    if (!treinador.getNome().equals(nomeTreinador)) {
        throw new RuntimeException("Nome do treinador não corresponde ao ID");
    }

    Pokemon pokemon = pokemonRepository.findById(idPokemon)
            .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

    if (!pokemon.getTreinador().getId().equals(idTreinador)) {
        throw new RuntimeException("Este Pokémon não pertence ao treinador informado");
    }

    if (pokemon.getNivel() > 1) {
        pokemon.setNivel(pokemon.getNivel() - 1);
    }
    return pokemonRepository.save(pokemon);
}

public Pokemon atualizarPokemon(Long idTreinador, String nomeTreinador, Long idPokemon, Pokemon novosDados) {
    Treinador treinador = treinadorRepository.findById(idTreinador)
            .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

    if (!treinador.getNome().equals(nomeTreinador)) {
        throw new RuntimeException("Nome do treinador não corresponde ao ID");
    }

    Pokemon pokemon = pokemonRepository.findById(idPokemon)
            .orElseThrow(() -> new RuntimeException("Pokémon não encontrado"));

    if (!pokemon.getTreinador().getId().equals(idTreinador)) {
        throw new RuntimeException("Este Pokémon não pertence ao treinador informado");
    }

    if (novosDados.getNome() != null) {
        pokemon.setNome(novosDados.getNome());
    }
    if (novosDados.getImagemUrl() != null) {
        pokemon.setImagemUrl(novosDados.getImagemUrl());
    }
    if (novosDados.getTipo() != null) {
        pokemon.setTipo(novosDados.getTipo());
    }
    if (novosDados.getNivel() != null) {
        pokemon.setNivel(novosDados.getNivel());
    }

    return pokemonRepository.save(pokemon);
}

public Pokemon getPokemonPorTreinador(Long treinadorId, Long pokemonId) {
        return pokemonRepository.findByIdAndTreinadorId(pokemonId, treinadorId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon não encontrado"));
    }


}
