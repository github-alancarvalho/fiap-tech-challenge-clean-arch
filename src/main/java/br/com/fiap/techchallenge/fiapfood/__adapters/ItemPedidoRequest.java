package br.com.fiap.techchallenge.fiapfood.__adapters;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPedidoRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idPedido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long idProduto;

    private int quantidade;

    public ItemPedidoRequest(Long idPedido, Long idProduto, int quantidade) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
