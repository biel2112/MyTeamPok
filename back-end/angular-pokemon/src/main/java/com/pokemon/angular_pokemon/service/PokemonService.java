package com.pokemon.angular_pokemon.service;

import com.pokemon.angular_pokemon.dto.AtualizarPokemonDto;
import com.pokemon.angular_pokemon.dto.PokemonDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.repository.PokemonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

// Essa classe contém toda a lógica de negócio do sistema.
@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository repository){
        this.pokemonRepository = repository;
    }

    // Retorna Pokémons paginados.
    public Page<Pokemon> listarPokemons(int pagina, int numeroElementos){
        PageRequest pageRequest = PageRequest.of(pagina, numeroElementos, Sort.by("id").ascending());
        return pokemonRepository.findAll(pageRequest);
    }

    // Cria um novo Pokémon.
    public Pokemon salvarPokemon(PokemonDto dto) {
        Pokemon pokemon = new Pokemon(null,dto.nome(), dto.tipo(), dto.nivel(), dto.imagemUrl());
        return pokemonRepository.save(pokemon);
    }

    // Atualiza um Pokémon pelo ID.
    public Pokemon atualizarPokemon(Long id, AtualizarPokemonDto dto){
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon não encontrado"));

        if(dto.nome() != null){
            pokemon.setNome(dto.nome());
        }
        if(dto.tipo() != null){
            pokemon.setTipo(dto.tipo());
        }
        if(dto.nivel() != null){
            pokemon.setNivel(dto.nivel());
        }
        if(dto.imagemUrl() != null){
            pokemon.setImagemUrl(dto.imagemUrl());
        }
        return pokemonRepository.save(pokemon);
    }

    // Busca um Pokemon pelo ID
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
