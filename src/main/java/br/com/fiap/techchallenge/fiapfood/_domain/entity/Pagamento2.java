package br.com.fiap.techchallenge.fiapfood._domain.entity;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagamento2 {

    private Cliente cliente;
    private Pedido pedido;
    private String status;
    private Double valor;
    private CartaoCredito cartaoCredito;

    public Pagamento2(Cliente cliente, Pedido pedido, String status, Double valor, CartaoCredito cartaoCredito) {
        this.cliente = cliente;
        this.pedido = pedido;
        this.status = status;
        this.valor = valor;
        this.cartaoCredito = cartaoCredito;
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
