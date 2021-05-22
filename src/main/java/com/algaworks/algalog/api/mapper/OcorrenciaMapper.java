package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.OcorrenciaDTO;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.OcorrenciaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        return  modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

}
