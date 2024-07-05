package br.com.fiap.techchallenge.fiapfood._domain.entity;

import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagamento {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idPedido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusPagamento status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double valor;

    public Pagamento() {

    }

    public Pagamento(Long id, Long idPedido, StatusPagamento status, Double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
