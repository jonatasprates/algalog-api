package com.algaworks.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne //muitas entregas possui um cliente
    private Cliente cliente;

    @Embedded //abastrair os dados para outra classe, mas fica os dados na mesma tabela
    private Destinatario destinatario;
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private LocalDateTime dataFinalizacao;

}
