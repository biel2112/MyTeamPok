package com.pokemon.angular_pokemon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.angular_pokemon.dto.TreinadorDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.repository.PokemonRepository;
import com.pokemon.angular_pokemon.repository.TreinadorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreinadorService {

    @Autowired
    private final TreinadorRepository treinadorRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Treinador> listarTodos() {
        return treinadorRepository.findAll();
    }

    public Treinador criarTreinador(TreinadorDto treinadorDto){
        Treinador treinador = new Treinador();
        treinador.setNome(treinadorDto.nome());
        return treinadorRepository.save(treinador);
    }

    public Optional<Treinador> buscarTreinador(Long id, String nome){
        return treinadorRepository.findByIdAndNome(id, nome);
    }

    public Treinador adicionarPokemon(Long id, String nome, Pokemon pokemon) {
        // Busca o treinador pelo ID e nome
        Treinador treinador = treinadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));

        if (!treinador.getNome().equals(nome)) {
            throw new RuntimeException("O nome do treinador não corresponde ao ID fornecido.");
        }

        // Associa o Pokémon ao treinador
        pokemon.setTreinador(treinador);
        treinador.getPokemons().add(pokemon);

        // Salva o Pokémon primeiro (se necessário)
        pokemonRepository.save(pokemon);

        // Salva e retorna o treinador atualizado
        return treinadorRepository.save(treinador);
    }

    public Treinador removerPokemon(Long treinadorId, String nomeTreinador, Long pokemonId) {
        Treinador treinador = treinadorRepository.findByIdAndNome(treinadorId, nomeTreinador)
            .orElseThrow(() -> new RuntimeException("Treinador não encontrado!"));
    
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
            .orElseThrow(() -> new RuntimeException("Pokémon não encontrado!"));
    
        if (!treinador.getPokemons().contains(pokemon)) {
            throw new RuntimeException("Esse Pokémon não pertence a esse treinador!");
        }
    
        // Desassocia o Pokémon do treinador
        pokemon.setTreinador(null);
        pokemonRepository.save(pokemon);
    
        // Remove o Pokémon da lista do treinador
        treinador.getPokemons().remove(pokemon);
    
        // Salva as mudanças no banco
        treinadorRepository.save(treinador);
    
        return treinador;
    }

    public List<Pokemon> listarPokemons(Long treinadorId, String nomeTreinador){
        Treinador treinador = treinadorRepository.findByIdAndNome(treinadorId, nomeTreinador)
                .orElseThrow(() -> new RuntimeException("Treinador não encontrado"));
        return treinador.getPokemons();
    }
    
}
