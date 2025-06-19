package com.fernandorocha.nubank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandorocha.nubank.dto.ContatoDTO;
import com.fernandorocha.nubank.model.Clientes;
import com.fernandorocha.nubank.model.Contato;
import com.fernandorocha.nubank.repository.ClientesRepository;
import com.fernandorocha.nubank.repository.ContatoRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid ContatoDTO dto) {

        Optional<Clientes> clientesOpt = clientesRepository.findById(dto.getClienteId());

        if (clientesOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }
        Contato contato = new Contato();
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        contato.setClientes(clientesOpt.get());
        contatoRepository.save(contato);

        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }

}
