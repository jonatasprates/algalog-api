package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.DestinatarioDTO;
import com.algaworks.algalog.api.dto.EntregaDTO;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
                    EntregaDTO entregaDTO = new EntregaDTO();
                    entregaDTO.setId(entrega.getId());
                    entregaDTO.setNomeCliente(entrega.getCliente().getNome());
                    entregaDTO.setDestinatario(new DestinatarioDTO());
                    entregaDTO.getDestinatario().setNome(entrega.getDestinatario().getNome());
                    entregaDTO.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                    entregaDTO.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                    entregaDTO.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                    entregaDTO.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                    entregaDTO.setTaxa(entrega.getTaxa());
                    entregaDTO.setStatus(entrega.getStatus());
                    entregaDTO.setDataPedido(entrega.getDataPedido());
                    entregaDTO.setDataFinalizacao(entrega.getDataFinalizacao());
                    return ResponseEntity.ok(entregaDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
