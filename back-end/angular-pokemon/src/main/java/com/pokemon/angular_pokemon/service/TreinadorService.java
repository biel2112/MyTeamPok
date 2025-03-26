package com.pokemon.angular_pokemon.service;
import com.pokemon.angular_pokemon.dto.TreinadorDto;
import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.repository.TreinadorRepository;

import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TreinadorService {

    private final TreinadorRepository treinadorRepository;
    private final PasswordEncoder passwordEncoder;

    public TreinadorService(TreinadorRepository treinadorRepository, PasswordEncoder passwordEncoder) {
        this.treinadorRepository = treinadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String codificarSenha(String senha){
        return passwordEncoder.encode(senha);
    }

    public Page<Treinador> listarTreinadores(int pagina, int numeroElementos){
        PageRequest pageRequest = PageRequest.of(pagina, numeroElementos, Sort.by("id").ascending());
        return treinadorRepository.findAll(pageRequest);
    }

    public Treinador cadastrarTreinador(TreinadorDto dto) {
        if (treinadorRepository.findByNome(dto.nome()).isPresent()) {
            throw new RuntimeException("Treinador já existe!");
        }
        Treinador treinador = new Treinador();
        treinador.setNome(dto.nome());
        treinador.setSenha(passwordEncoder.encode(dto.senha()));
        return treinadorRepository.save(treinador);
    }

    public Treinador autenticarTreinador(TreinadorDto dto) {
        Treinador treinador = treinadorRepository.findByNome(dto.nome())
                .orElseThrow(() -> new RuntimeException("Nome ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(), treinador.getSenha())) {
            throw new RuntimeException("Nome ou senha inválidos");
        }

        return treinador;
    }
}
