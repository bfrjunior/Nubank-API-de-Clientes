package com.fernandorocha.nubank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandorocha.nubank.dto.ClientesDTO;
import com.fernandorocha.nubank.dto.ClientesResponseDTO;
import com.fernandorocha.nubank.dto.ContatoReponseDTO;
import com.fernandorocha.nubank.model.Clientes;
import com.fernandorocha.nubank.service.ClientesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> criar(@RequestBody ClientesDTO dto) {

        Clientes clienteSalvo = clientesService.salvarClientes(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clientesService.listarTodos());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoReponseDTO>> listarContatos(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.listarContatoPorCliente(id));
    }

}
