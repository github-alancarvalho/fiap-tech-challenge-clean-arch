package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ItemPedidoORM;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.PedidoORM;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoMapper {

    private ItemPedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ItemPedido mapToEntity(ItemPedidoORM entity) {
        if (entity == null) {
            return null;
        }

        return new ItemPedido(
                PedidoMapper.mapToEntity(entity.getPedido()),
                ProdutoMapper.mapToEntity(entity.getProduto()),
                entity.getQuantidade()
        );
    }

    public static ItemPedidoORM mapToEntity(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return null;
        }

        return new ItemPedidoORM(
                PedidoMapper.mapToEntity(itemPedido.getPedido()),
                ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                itemPedido.getQuantidade()
        );
    }

    public static List<ItemPedido> mapListToORM(List<ItemPedidoORM> listEntity) {
        List<ItemPedido> list = new ArrayList<>();
        for (ItemPedidoORM itemPedidoORM : listEntity) {
            list.add(ItemPedido.builder()
                    .pedido(Pedido.builder().id(itemPedidoORM.getPedido().getId()).build())
                    .produto(ProdutoMapper.mapToEntity(itemPedidoORM.getProduto()))
                    .quantidade(itemPedidoORM.getQuantidade())
                    .build()
            );
        }
        return list;
    }

    public static List<ItemPedidoORM> mapListToEntity(List<ItemPedido> listItemPedido) {
        List<ItemPedidoORM> list = new ArrayList<>();
        for (ItemPedido itemPedido : listItemPedido) {
            ItemPedidoORM itemPedidoORMEntity = new ItemPedidoORM(
                    PedidoMapper.mapToEntity(itemPedido.getPedido()),
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoORMEntity);
        }
        return list;
    }

    public static List<ItemPedidoORM> mapListaSimplesToEntity(List<ItemPedido> listItemPedido) {
        List<ItemPedidoORM> list = new ArrayList<>();
        for (ItemPedido itemPedido : listItemPedido) {
            PedidoORM pedidoORMEntity = new PedidoORM();
            if(itemPedido.getPedido()!=null && itemPedido.getPedido().getId() != null)
                pedidoORMEntity.setId(itemPedido.getPedido().getId());


            ItemPedidoORM itemPedidoORMEntity = new ItemPedidoORM(
                    pedidoORMEntity,
                    ProdutoMapper.mapToEntity(itemPedido.getProduto()),
                    itemPedido.getQuantidade()
            );
            list.add(itemPedidoORMEntity);
        }
        return list;
    }

}