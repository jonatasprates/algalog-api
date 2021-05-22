package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.ValidationGroups;
import com.algaworks.algalog.domain.exception.NegocioException;
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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "entrega", cascade =  CascadeType.ALL) // uma entreva possui várias ocorrencias mappedBy -> recebe o nome da dona do relacionamento inverso neste caso é a entrega, que esta na entidade entrega
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

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
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Campo apenas leitura
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.ocorrencias.add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }
}
