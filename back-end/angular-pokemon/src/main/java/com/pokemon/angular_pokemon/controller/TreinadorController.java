package com.pokemon.angular_pokemon.controller;

import com.pokemon.angular_pokemon.dto.TreinadorDto;
import com.pokemon.angular_pokemon.model.Pokemon;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.service.TreinadorService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinadores")
public class TreinadorController {

    private final TreinadorService treinadorService;

    public TreinadorController(TreinadorService treinadorService) {
        this.treinadorService = treinadorService;
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<Treinador>> listarTreinadores(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int numeroElementos) {

        Page<Treinador> treinadores = treinadorService.listarTreinadores(pagina, numeroElementos);
        return ResponseEntity.ok(treinadores);
    }


    // Cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<Treinador> cadastrar(@RequestBody TreinadorDto dto) {
        return ResponseEntity.ok(treinadorService.cadastrarTreinador(dto));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody TreinadorDto dto) {
        Treinador treinador = treinadorService.autenticarTreinador(dto);
        return ResponseEntity.ok("Login bem-sucedido! Bem-vindo, " + treinador.getNome());
    }
}
