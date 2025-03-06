package com.pokemon.angular_pokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Classe responsável por representar um Pokemon no Banco de Dados

@Entity
@Table(name = "pokemons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer nivel;

    @Column(nullable = false)
    private String imagemUrl;

}
