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
