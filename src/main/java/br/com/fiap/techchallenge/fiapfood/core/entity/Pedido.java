package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pedido {

    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Cliente cliente;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusPedido status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ItemPedido> listItens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, StatusPedido status, LocalDateTime data, List<ItemPedido> listItens) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.data = data;
        this.listItens = listItens;
    }

    public Pedido confirmarPagamento(Pagamento pagamento) {
        if (pagamento == null) {
            throw new FiapFoodException("Pagamento nulo");
        }
        if (pagamento.getIdPagamento() == null) {
            throw new FiapFoodException("Pagamento deve estar gravado");
        }
        if (status != null) {
            throw new FiapFoodException("Status invalido para pagamento: " + status);
        }
        if (pagamento.getStatus() == StatusPagamento.EM_PROCESSAMENTO.toString()) {
            throw new FiapFoodException("Pagamento em processamento");
        }

        return Pedido.builder().id(id).status(status).cliente(cliente).listItens(listItens).data(LocalDateTime.now()).build();
    }

    public Pedido validar() {
        if (status != StatusPedido.RECEBIDO) {
            throw new FiapFoodException("Status invalido para validação do pedido: " + status);
        }
        return Pedido.builder().id(id).status(status).cliente(cliente).listItens(listItens).data(LocalDateTime.now()).build();
    }

    public Pedido setPronto() {
        if (status != StatusPedido.EM_PREPARACAO) {
            throw new FiapFoodException("Status invalido para mudar para Pronto: " + status);
        }
        return Pedido.builder().id(id).status(status).cliente(cliente).listItens(listItens).data(LocalDateTime.now()).build();
    }

    public Pedido finalizar() {
        if (status != StatusPedido.PRONTO) {
            throw new FiapFoodException("Status invalido para finalizar: " + status);
        }
        return Pedido.builder().id(id).status(status).cliente(cliente).listItens(listItens).data(LocalDateTime.now()).build();
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemPedido> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedido> listItens) {
        this.listItens = listItens;
    }
}
