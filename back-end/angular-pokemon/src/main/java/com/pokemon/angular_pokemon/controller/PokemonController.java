package com.pokemon.angular_pokemon.controller;


import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping("/treinadores/{idTreinador}/{nomeTreinador}/pokemons/{id}/subir-nivel")
    public ResponseEntity<Pokemon> subirNivel(
        @PathVariable Long idTreinador,
        @PathVariable String nomeTreinador,
        @PathVariable Long id
        ) {
        Pokemon atualizado = pokemonService.subirNivel(idTreinador, nomeTreinador, id);
        return ResponseEntity.ok(atualizado);
    }

    // Diminui o nível do Pokémon.
    @PatchMapping("/treinadores/{idTreinador}/{nomeTreinador}/pokemons/{id}/diminuir-nivel")
    public ResponseEntity<Pokemon> diminuirNivel(@PathVariable Long idTreinador,
    @PathVariable String nomeTreinador,
    @PathVariable Long id) {
        Pokemon atualizado = pokemonService.diminuirNivel(idTreinador, nomeTreinador, id);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/treinadores/{idTreinador}/{nomeTreinador}/pokemons/{id}")
    public ResponseEntity<Pokemon> atualizarPokemon(
        @PathVariable Long idTreinador,
        @PathVariable String nomeTreinador,
        @PathVariable Long id,
        @RequestBody Pokemon novosDados) {
    Pokemon atualizado = pokemonService.atualizarPokemon(idTreinador, nomeTreinador, id, novosDados);
    return ResponseEntity.ok(atualizado);
}


}
