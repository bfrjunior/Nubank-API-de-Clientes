package com.fernandorocha.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandorocha.nubank.model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

}
