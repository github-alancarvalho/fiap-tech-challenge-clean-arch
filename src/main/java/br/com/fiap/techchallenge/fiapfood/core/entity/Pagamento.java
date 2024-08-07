package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Pagamento {

    private Long idPagamento;
    private Cliente cliente;
    private Pedido pedido;
    private String status;
    private Double valor;
    private CartaoCredito cartaoCredito;

    public Pagamento() {

    }

    public Pagamento(Long idPagamento, Cliente cliente, Pedido pedido, String status, Double valor, CartaoCredito cartaoCredito) {
        this.idPagamento = idPagamento;
        this.cliente = cliente;
        this.pedido = pedido;
        this.status = status;
        this.valor = valor;
        this.cartaoCredito = cartaoCredito;
    }

    public Pagamento criarPagamentoSemClienteSemDadosDeCartao(Long idPagamento, Long idPedido, StatusPagamento status, Double valor) {

        return Pagamento.builder()
                .idPagamento(idPagamento)
                .pedido(Pedido.builder().id(idPedido).build())
                .status(status.toString())
                .valor(valor)
                .build();

    }

    public Pagamento confirmarPagamento() {
        if(!status.equals(StatusPagamento.APROVADO.toString()))
            throw new FiapFoodException("Pagamento não consta como aprovado até o momento");

        return Pagamento.builder()
                .idPagamento(idPagamento)
                .pedido(pedido)
                .status(status)
                .valor(valor)
                .cartaoCredito(cartaoCredito)
                .build();
    }


    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
}
