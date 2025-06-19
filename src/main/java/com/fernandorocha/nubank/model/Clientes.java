package com.fernandorocha.nubank.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Clientes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "clientes", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonManagedReference
    private List<Object> contatos = new ArrayList<Object>();

}
