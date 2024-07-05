package br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "item_pedido")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoORM {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PedidoORM pedidoORM;

    @Id
    @ManyToOne
    private ProdutoORM produtoORM;

    private int quantidade;

    public ItemPedidoORM() {
    }

    public ItemPedidoORM(PedidoORM pedidoORM, ProdutoORM produtoORM, int quantidade) {
        this.pedidoORM = pedidoORM;
        this.produtoORM = produtoORM;
        this.quantidade = quantidade;
    }

    public PedidoORM getPedido() {
        return pedidoORM;
    }

    public void setPedido(PedidoORM pedidoORM) {
        this.pedidoORM = pedidoORM;
    }

    public ProdutoORM getProduto() {
        return produtoORM;
    }

    public void setProduto(ProdutoORM produtoORM) {
        this.produtoORM = produtoORM;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoORM)) return false;
        ItemPedidoORM that = (ItemPedidoORM) o;
        return quantidade == that.quantidade && Objects.equals(pedidoORM, that.pedidoORM) && Objects.equals(produtoORM, that.produtoORM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoORM, produtoORM, quantidade);
    }
}
