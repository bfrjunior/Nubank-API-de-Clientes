package com.fernandorocha.nubank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandorocha.nubank.dto.ClientesDTO;
import com.fernandorocha.nubank.dto.ClientesResponseDTO;
import com.fernandorocha.nubank.dto.ContatoDTO;
import com.fernandorocha.nubank.dto.ContatoReponseDTO;
import com.fernandorocha.nubank.model.Clientes;
import com.fernandorocha.nubank.model.Contato;
import com.fernandorocha.nubank.repository.ClientesRepository;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarClientes(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if (dto.getContatos() != null && !dto.getContatos().isEmpty()) {
            List<Contato> contatos = dto.getContatos().stream()
                    .map(c -> criarContato(c, clientes))
                    .collect(Collectors.toList());
            clientes.setContatos(contatos);
        }

        return clientesRepository.save(clientes);
    }

    public List<ClientesResponseDTO> listarTodos() {
        return clientesRepository.findAll().stream()
                .map(this::toClienteResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ContatoReponseDTO> listarContatoPorCliente(Long clienteId) {
        Clientes cliente = clientesRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente.getContatos().stream()
                .map(this::toContatoResponseDTO)
                .collect(Collectors.toList());
    }

    private Contato criarContato(ContatoDTO contatoDTO, Clientes cliente) {
        Contato contato = new Contato();
        contato.setTelefone(contatoDTO.getTelefone());
        contato.setEmail(contatoDTO.getEmail());
        contato.setClientes(cliente);
        return contato;
    }

    private ContatoReponseDTO toContatoResponseDTO(Contato contato) {
        ContatoReponseDTO dto = new ContatoReponseDTO();
        dto.setId(contato.getId());
        dto.setTelefone(contato.getTelefone());
        dto.setEmail(contato.getEmail());
        return dto;
    }

    private ClientesResponseDTO toClienteResponseDTO(Clientes cliente) {
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setContatos(cliente.getContatos().stream()
                .map(this::toContatoResponseDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}