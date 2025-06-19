package com.fernandorocha.nubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fernandorocha.nubank.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
