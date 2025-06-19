package com.fernandorocha.nubank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandorocha.nubank.dto.ClientesDTO;
import com.fernandorocha.nubank.dto.ClientesResponseDTO;
import com.fernandorocha.nubank.dto.ContatoReponseDTO;
import com.fernandorocha.nubank.model.Clientes;
import com.fernandorocha.nubank.model.Contato;
import com.fernandorocha.nubank.repository.ClientesRepository;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    private Clientes salvarClientes(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if (dto.getContatos() != null && dto.getContatos().size() > 0) {
            List<Contato> contatos = dto.getContatos().stream().map(c -> {
                Contato contato = new Contato();
                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            }).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }

        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos() {
        return clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());

    }

    public List<ContatoReponseDTO> listarContatoPorCliente(Long clienteId) {
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getContatos().stream().map(c -> {
            ContatoReponseDTO dto = new ContatoReponseDTO();
            dto.setId(c.getId());
            dto.setTelefone(c.getTelefone());
            dto.setEmail(c.getEmail());
            return dto;
        }).collect(Collectors.toList());

    }

    private ClientesResponseDTO toDTO(Clientes cliente) {

        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoReponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoReponseDTO contatoDTO = new ContatoReponseDTO();
            contatoDTO.setTelefone(c.getTelefone());
            contatoDTO.setEmail(c.getEmail());
            return contatoDTO;
        }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;
    }
}