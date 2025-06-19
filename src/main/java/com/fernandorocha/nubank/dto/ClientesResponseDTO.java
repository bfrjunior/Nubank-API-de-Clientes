package com.fernandorocha.nubank.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientesResponseDTO {

    private Long id;
    private String nome;
    private List<ContatoReponseDTO> contatos;

}
