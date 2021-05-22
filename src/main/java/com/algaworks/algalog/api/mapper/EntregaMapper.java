package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.EntregaDTO;
import com.algaworks.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper;

    public EntregaDTO toDTO(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
        return entregas.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
