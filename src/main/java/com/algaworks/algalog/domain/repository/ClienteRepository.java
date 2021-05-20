package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    // Pesquisa pelo nome usando o link, ou seja procura o nome que contém caracter espefica
    // List<Cliente> findByNomeContaining(String nome);
}
