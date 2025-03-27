package com.pokemon.angular_pokemon.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "treinadores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "treinadores", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    
    private List<Pokemon> pokemons = new ArrayList<Pokemon>();

    public void adicionarPokemon(Pokemon pokemon){
        if(pokemons.size() < 6){
            pokemons.add(pokemon);
            pokemon.setTreinador(this);
        }
    }
    
}
