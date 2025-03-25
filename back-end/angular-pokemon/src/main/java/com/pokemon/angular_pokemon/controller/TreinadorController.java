package com.pokemon.angular_pokemon.controller;

import com.pokemon.angular_pokemon.dto.TreinadorDto;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.service.TreinadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treinadores")
public class TreinadorController {

    private final TreinadorService treinadorService;

    public TreinadorController(TreinadorService treinadorService) {
        this.treinadorService = treinadorService;
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
