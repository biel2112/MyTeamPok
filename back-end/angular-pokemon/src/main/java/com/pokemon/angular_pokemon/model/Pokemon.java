package com.pokemon.angular_pokemon.model;

import jakarta.persistence.*;



import lombok.*;

// Classe responsável por representar um Pokemon no Banco de Dados

@Entity
@Table(name = "pokemons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer nivel;

    @Column(nullable = false)
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "treinador_id", nullable = true)
    private Treinador treinador;

}
