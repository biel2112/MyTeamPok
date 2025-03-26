package com.pokemon.angular_pokemon.service;

import com.pokemon.angular_pokemon.model.Treinador;
import com.pokemon.angular_pokemon.repository.TreinadorRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    // Chave secreta para assinar o JWT (em um ambiente real, isso deve ser protegida)
    private static final String SECRET_KEY = "minhaChaveSecreta";

    @Autowired
    private TreinadorRepository treinadorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Método para autenticar o usuário
    public String autenticar(String nome, String senha) {
        // Busca o treinador no banco de dados
        Treinador treinador = treinadorRepository.findByNome(nome).orElse(null);
    
        // Verifica se o treinador existe e se a senha está correta
        if (treinador != null && passwordEncoder.matches(senha, treinador.getSenha())) {
            // Gera o token JWT se a autenticação for bem-sucedida
            return gerarToken(nome);
        }
    
        // Retorna null caso a autenticação falhe
        return null;
    }

    // Método para gerar o token JWT
    private String gerarToken(String nome) {
        // Configurações do JWT
        long now = System.currentTimeMillis();
        long expirationTime = 1000 * 60 * 60; // Token válido por 1 hora

        return Jwts.builder()
                .setSubject(nome)  // Define o nome como assunto
                .setIssuedAt(new Date(now))  // Data de emissão
                .setExpiration(new Date(now + expirationTime))  // Data de expiração
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Assina o token com a chave secreta
                .compact();  // Gera o token
    }
}

