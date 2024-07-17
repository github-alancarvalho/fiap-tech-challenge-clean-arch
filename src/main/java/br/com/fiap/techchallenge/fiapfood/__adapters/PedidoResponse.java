package br.com.fiap.techchallenge.fiapfood.__adapters;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoResponse {

    private Long id;
    private Cliente cliente;
    private StatusPedido status;
    private LocalDateTime data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemPedido> listItens = new ArrayList<>();

    public PedidoResponse() {
    }

    public PedidoResponse(Long id, Cliente cliente, StatusPedido status, LocalDateTime data, List<ItemPedido> listItens) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.listItens = listItens;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
