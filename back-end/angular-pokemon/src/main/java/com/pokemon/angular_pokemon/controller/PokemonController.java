package com.pokemon.angular_pokemon.controller;

import com.pokemon.angular_pokemon.dto.AtualizarPokemonDto;
import com.pokemon.angular_pokemon.dto.PokemonDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.service.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/*
 * Classe responsável por expor os endpoints para operações CRUD sobre os Pokémons.
 */
@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "http://localhost:4200")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService service){
        this.pokemonService = service;
    }

    // Retorna uma lista paginada de Pokémons.
    @GetMapping
    public ResponseEntity<Page<Pokemon>> listarPokemon(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "6") int numeroElementos) {

        Page<Pokemon> pokemons = pokemonService.listarPokemons(pagina, numeroElementos);
        return ResponseEntity.ok(pokemons);
    }

    // Cria um novo Pokémon.
    @PostMapping
    public ResponseEntity<Pokemon> salvarPokemon(@RequestBody PokemonDto dto) {
        Pokemon novoPokemon = pokemonService.salvarPokemon(dto);
        URI location = URI.create("/pokemons/" + novoPokemon.getId()); // Alterado para /pokemons/{id}
        return ResponseEntity.created(location).body(novoPokemon);
    }

    // Atualiza um Pokémon por completo.
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> atualizarPokemon(@PathVariable Long id, @RequestBody AtualizarPokemonDto dto){
        Pokemon pokemonAtualizado = pokemonService.atualizarPokemon(id, dto);
        return ResponseEntity.ok(pokemonAtualizado);
    }

    // Atualiza uma informação de um Pokémon.
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> atualizarPokemonParcial(@PathVariable Long id, @RequestBody AtualizarPokemonDto dto){
        Pokemon pokemonAtualizadoParcial = pokemonService.atualizarPokemon(id, dto);
        return ResponseEntity.ok(pokemonAtualizadoParcial);
    }

    // Retorna um Pokémon específico pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.buscarPorId(id));
    }

    // Remove um Pokémon do banco de dados.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pokemonService.deletarPokemon(id);
        return ResponseEntity.noContent().build();
    }

    // Aumenta o nível do Pokémon.
    @PatchMapping("/{id}/subir-nivel")
    public ResponseEntity<Pokemon> subirNivel(@PathVariable Long id) {
        Pokemon atualizado = pokemonService.subirNivel(id);
        return ResponseEntity.ok(atualizado);
    }

    // Diminui o nível do Pokémon.
    @PatchMapping("/{id}/diminuir-nivel")
    public ResponseEntity<Pokemon> diminuirNivel(@PathVariable Long id) {
        Pokemon atualizado = pokemonService.diminuirNivel(id);
        return ResponseEntity.ok(atualizado);
    }

}
