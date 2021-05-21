package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid //validar objeto e todos os atributos que esta dentro de cliente
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) //converter um Group Validation por outro
    @NotNull
    @ManyToOne //muitas entregas possui um cliente
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded //abastrair os dados para outra classe, mas fica os dados na mesma tabela
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private LocalDateTime dataFinalizacao;

}
