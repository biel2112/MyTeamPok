package com.pokemon.angular_pokemon.controller;

import com.pokemon.angular_pokemon.dto.AtualizarPokemonDto;
import com.pokemon.angular_pokemon.dto.PokemonDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.service.PokemonService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "http://localhost:4200")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService service){
        this.pokemonService = service;
    }

    @GetMapping
    public ResponseEntity<Page<Pokemon>> listarPokemon(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "6") int numeroElementos) {

        Page<Pokemon> pokemons = pokemonService.listarPokemons(pagina, numeroElementos);
        return ResponseEntity.ok(pokemons);
    }

    @PostMapping
    public ResponseEntity<Pokemon> salvarPokemon(@RequestBody PokemonDto dto) {
        Pokemon novoPokemon = pokemonService.salvarPokemon(dto);
        URI location = URI.create("/pokemons/" + novoPokemon.getId()); // Alterado para /pokemons/{id}
        return ResponseEntity.created(location).body(novoPokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> atualizarPokemon(@PathVariable Long id, @RequestBody AtualizarPokemonDto dto){
        Pokemon pokemonAtualizado = pokemonService.atualizarPokemon(id, dto);
        return ResponseEntity.ok(pokemonAtualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> atualizarPokemonParcial(@PathVariable Long id, @RequestBody AtualizarPokemonDto dto){
        Pokemon pokemonAtualizadoParcial = pokemonService.atualizarPokemon(id, dto);
        return ResponseEntity.ok(pokemonAtualizadoParcial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pokemonService.buscarPorId(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pokemonService.deletarPokemon(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/subir-nivel")
    public ResponseEntity<Pokemon> subirNivel(@PathVariable Long id) {
        Pokemon atualizado = pokemonService.subirNivel(id);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}/diminuir-nivel")
    public ResponseEntity<Pokemon> diminuirNivel(@PathVariable Long id) {
        Pokemon atualizado = pokemonService.diminuirNivel(id);
        return ResponseEntity.ok(atualizado);
    }

}
