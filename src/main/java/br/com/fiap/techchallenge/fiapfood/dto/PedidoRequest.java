package br.com.fiap.techchallenge.fiapfood.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PedidoRequest {

    private Long id;
    private String cpfCliente;
    private String status;
    private DadosPagamentoRequest dadosPagamento;
    private List<ItemPedidoRequest> listItens = new ArrayList<>();

    public PedidoRequest() {
    }

    public PedidoRequest(Long id, String cpfCliente, String status, DadosPagamentoRequest dadosPagamento, List<ItemPedidoRequest> listItens) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.status = status;
        this.listItens = listItens;
        this.dadosPagamento = dadosPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemPedidoRequest> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoRequest> listItens) {
        this.listItens = listItens;
    }

    public DadosPagamentoRequest getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(DadosPagamentoRequest dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }
}
