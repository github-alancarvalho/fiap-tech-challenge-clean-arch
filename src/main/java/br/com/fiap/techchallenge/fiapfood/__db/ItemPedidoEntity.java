package br.com.fiap.techchallenge.fiapfood.__db;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_pedido")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoEntity implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PedidoEntity pedidoEntity;

    @Id
    @ManyToOne
    private ProdutoEntity produtoEntity;

    private int quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(PedidoEntity pedidoEntity, ProdutoEntity produtoEntity, int quantidade) {
        this.pedidoEntity = pedidoEntity;
        this.produtoEntity = produtoEntity;
        this.quantidade = quantidade;
    }

    public PedidoEntity getPedido() {
        return pedidoEntity;
    }

    public void setPedido(PedidoEntity pedidoEntity) {
        this.pedidoEntity = pedidoEntity;
    }

    public ProdutoEntity getProduto() {
        return produtoEntity;
    }

    public void setProduto(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
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
        if (!(o instanceof ItemPedidoEntity)) return false;
        ItemPedidoEntity that = (ItemPedidoEntity) o;
        return quantidade == that.quantidade && Objects.equals(pedidoEntity, that.pedidoEntity) && Objects.equals(produtoEntity, that.produtoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoEntity, produtoEntity, quantidade);
    }
}
