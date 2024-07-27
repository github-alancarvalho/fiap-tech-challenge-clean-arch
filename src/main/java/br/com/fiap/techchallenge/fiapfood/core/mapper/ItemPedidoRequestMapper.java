package br.com.fiap.techchallenge.fiapfood.core.mapper;

import br.com.fiap.techchallenge.fiapfood.__adapters.ItemPedidoRequest;
import br.com.fiap.techchallenge.fiapfood.core.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoRequestMapper {

    private ItemPedidoRequestMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ItemPedido> mapFromRequestToDomain(List<ItemPedidoRequest> listItemPedidoRequest) {
        if (listItemPedidoRequest == null) {
            return null;
        }

        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoRequest itemPedidoRequest : listItemPedidoRequest) {
            list.add(ItemPedido.builder()
                    .pedido(Pedido.builder().id(itemPedidoRequest.getIdPedido()).build())
                    .produto(Produto.builder().id(itemPedidoRequest.getIdProduto()).build())
                    .quantidade(itemPedidoRequest.getQuantidade()).build());
        }

        return list;
    }
}