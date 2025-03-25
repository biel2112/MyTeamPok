package com.pokemon.angular_pokemon.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treinadores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL)
    private List<Pokemon> timePrincipal;

    @OneToMany(mappedBy = "treinador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pokemon> computador;

    
}