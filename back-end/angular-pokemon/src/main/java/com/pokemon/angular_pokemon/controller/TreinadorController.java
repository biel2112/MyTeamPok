package com.pokemon.angular_pokemon.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pokemon.angular_pokemon.dto.TreinadorDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.service.PokemonService;
import com.pokemon.angular_pokemon.service.TreinadorService;

@RestController
@RequestMapping("/treinadores")
public class TreinadorController {

    @Autowired
    private TreinadorService treinadorService;

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Treinador>> listarTreinadores() {
        List<Treinador> treinadores = treinadorService.listarTodos();
        return ResponseEntity.ok(treinadores);
    }

    @PostMapping
    public ResponseEntity<Treinador> criarTreinador(@RequestBody TreinadorDto treinadorDto){
        Treinador treinador = treinadorService.criarTreinador(treinadorDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/{nome}")
                .buildAndExpand(treinador.getId(), treinador.getNome())
                .toUri();
        return ResponseEntity.created(location).body(treinador);
    }

    @GetMapping("/{id}/{nome}")
    public ResponseEntity<Treinador> buscarTreinador(@PathVariable Long id, @PathVariable String nome) {
        return treinadorService.buscarTreinador(id, nome)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Adicionar um Pokémon ao treinador
    @PostMapping("/{id}/{nome}/pokemons/new")
    public ResponseEntity<Treinador> adicionarPokemon(
            @PathVariable Long id, 
            @PathVariable String nome,
            @RequestBody Pokemon pokemon) {
        
        Treinador treinadorAtualizado = treinadorService.adicionarPokemon(id, nome, pokemon);
        return ResponseEntity.ok(treinadorAtualizado);
    }

    // Remover um Pokémon do treinador
    @DeleteMapping("/{treinadorId}/{nomeTreinador}/pokemons/delete/{pokemonId}")
    public ResponseEntity<Treinador> removerPokemon(
            @PathVariable Long treinadorId, 
            @PathVariable String nomeTreinador, 
            @PathVariable Long pokemonId) {
        Treinador treinador = treinadorService.removerPokemon(treinadorId, nomeTreinador, pokemonId);
        return ResponseEntity.ok(treinador);
    }

    // Listar os Pokémons de um treinador
    @GetMapping("/{treinadorId}/{nomeTreinador}/pokemons")
    public ResponseEntity<List<Pokemon>> listarPokemons(
        @PathVariable Long treinadorId, 
        @PathVariable String nomeTreinador) {
        List<Pokemon> pokemons = treinadorService.listarPokemons(treinadorId, nomeTreinador);
        return ResponseEntity.ok(pokemons);
}

    // Aumenta o nível do Pokémon.
    @PatchMapping("/{idTreinador}/{nomeTreinador}/pokemons/{id}/subir-nivel")
    public ResponseEntity<Pokemon> subirNivel(
        @PathVariable Long idTreinador,
        @PathVariable String nomeTreinador,
        @PathVariable Long id
        ) {
        Pokemon atualizado = pokemonService.subirNivel(idTreinador, nomeTreinador, id);
        return ResponseEntity.ok(atualizado);
    }

    // Diminui o nível do Pokémon.
    @PatchMapping("/{idTreinador}/{nomeTreinador}/pokemons/{id}/diminuir-nivel")
    public ResponseEntity<Pokemon> diminuirNivel(@PathVariable Long idTreinador,
    @PathVariable String nomeTreinador,
    @PathVariable Long id) {
        Pokemon atualizado = pokemonService.diminuirNivel(idTreinador, nomeTreinador, id);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{idTreinador}/{nomeTreinador}/pokemons/edit/{id}")
    public ResponseEntity<Pokemon> atualizarPokemon(
        @PathVariable Long idTreinador,
        @PathVariable String nomeTreinador,
        @PathVariable Long id,
        @RequestBody Pokemon novosDados) {
    Pokemon atualizado = pokemonService.atualizarPokemon(idTreinador, nomeTreinador, id, novosDados);
    return ResponseEntity.ok(atualizado);
}

@GetMapping("/{idTreinador}/pokemons/{pokemonId}")
    public ResponseEntity<Pokemon> getPokemonPorTreinador(
        @PathVariable Long idTreinador,
        @PathVariable Long pokemonId
    ) {
        Pokemon pokemon = pokemonService.getPokemonPorTreinador(idTreinador, pokemonId);
        return ResponseEntity.ok(pokemon);
    }

    
}
