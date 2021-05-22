package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OcorrenciaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = getEntrega(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

    public Entrega getEntrega(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
        return entrega;
    }
}
