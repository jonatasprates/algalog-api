package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.DestinatarioDTO;
import com.algaworks.algalog.api.dto.EntregaDTO;
import com.algaworks.algalog.api.mapper.EntregaMapper;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaMapper entregaMapper;

    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitar(@Valid @RequestBody Entrega entrega) {
        Entrega solicitar = solicitacaoEntregaService.solicitar(entrega);
        return entregaMapper.toDTO(solicitar);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return entregaMapper.toCollectionDTO(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toDTO(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }
}
