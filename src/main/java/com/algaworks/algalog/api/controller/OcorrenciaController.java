package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.OcorrenciaDTO;
import com.algaworks.algalog.api.mapper.OcorrenciaMapper;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId,
                                   @Valid @RequestBody Ocorrencia ocorrencia) {
        Ocorrencia registrada = ocorrenciaService.registrar(entregaId, ocorrencia.getDescricao());

        return ocorrenciaMapper.toDTO(registrada);

    }

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        Entrega entrega = ocorrenciaService.getEntrega(entregaId);

        return ocorrenciaMapper.toCollectionDTO(entrega.getOcorrencias());
    }
}
