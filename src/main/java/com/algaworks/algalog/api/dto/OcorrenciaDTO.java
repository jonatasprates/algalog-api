package com.algaworks.algalog.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaDTO {

    private Long id;
    @NotBlank
    private String descricao;
    private OffsetDateTime dataRegistro;
}
