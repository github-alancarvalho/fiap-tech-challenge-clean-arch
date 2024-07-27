package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagamento2 {

    private Long idPagamento;
    private Cliente cliente;
    private Pedido pedido;
    private String status;
    private Double valor;
    private CartaoCredito cartaoCredito;

    public Pagamento2() {

    }

    public Pagamento2(Long idPagamento, Cliente cliente, Pedido pedido, String status, Double valor, CartaoCredito cartaoCredito) {
        this.idPagamento = idPagamento;
        this.cliente = cliente;
        this.pedido = pedido;
        this.status = status;
        this.valor = valor;
        this.cartaoCredito = cartaoCredito;
    }

    public Pagamento2 criarPagamentoSemClienteSemDadosDeCartao(Long idPagamento, Long idPedido, StatusPagamento status, Double valor) {

        return Pagamento2.builder()
                .idPagamento(idPagamento)
                .pedido(Pedido.builder().id(idPedido).build())
                .status(status.toString())
                .valor(valor)
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
