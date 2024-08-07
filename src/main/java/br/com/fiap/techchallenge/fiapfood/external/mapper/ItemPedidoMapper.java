package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.core.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.external.entities.ItemPedidoEntity;
import br.com.fiap.techchallenge.fiapfood.external.entities.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoMapper {

    private ItemPedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ItemPedido mapToEntity(ItemPedidoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ItemPedido(
                PedidoMapper.mapToEntity(entity.getPedido()),
                ProdutoMapper.mapToEntity(entity.getProduto()),
                entity.getQuantidade()
        );
    }

    public static ItemPedidoEntity mapToEntity(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        return new ItemPedidoEntity(
                PedidoMapper.mapToEntity(itemPedido.getPedido()),
                ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade()
        );
    }

    public static List<ItemPedido> mapListToORM(List<ItemPedidoEntity> listEntity) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoEntity itemPedidoEntity : listEntity) {
            list.add(ItemPedido.builder()
                    .pedido(Pedido.builder().id(itemPedidoEntity.getPedido().getId()).build())
                    .produto(ProdutoMapper.mapToEntity(itemPedidoEntity.getProduto()))
                    .quantidade(itemPedidoEntity.getQuantidade())
                    .build()
            );
        }
        return list;
    }

    public static List<ItemPedidoEntity> mapListToEntity(List<ItemPedido> listItemPedido) {
        List<ItemPedidoEntity> list = new ArrayList<>();
        for (ItemPedido itemPedido : listItemPedido) {
            ItemPedidoEntity itemPedidoEntityEntity = new ItemPedidoEntity(
                    PedidoMapper.mapToEntity(itemPedido.getPedido()),
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntityEntity);
        }
        return list;
    }

    public static List<ItemPedidoEntity> mapListaSimplesToEntity(List<ItemPedido> listItemPedido) {
        List<ItemPedidoEntity> list = new ArrayList<>();
        for (ItemPedido itemPedido : listItemPedido) {
            PedidoEntity pedidoEntityEntity = new PedidoEntity();
            if(itemPedido.getPedido()!=null && itemPedido.getPedido().getId() != null)
                pedidoEntityEntity.setId(itemPedido.getPedido().getId());


            ItemPedidoEntity itemPedidoEntityEntity = new ItemPedidoEntity(
                    pedidoEntityEntity,
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoEntityEntity);
        }
        return list;
    }

}