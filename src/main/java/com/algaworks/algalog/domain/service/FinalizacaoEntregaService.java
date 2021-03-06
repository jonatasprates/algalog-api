package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FinalizacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = ocorrenciaService.getEntrega(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);

    }
}
