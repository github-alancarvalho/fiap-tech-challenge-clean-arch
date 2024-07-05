package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.PedidoORM;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    private PedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pedido mapToEntity(PedidoORM entity) {
        if (entity == null) {
            return null;
        }

        return new Pedido(
                entity.getId(),
                ClienteMapper.mapToEntity(entity.getCliente()),
                entity.getStatus(),
                ItemPedidoMapper.mapListToORM(entity.getListItens())
        );
    }

    public static PedidoORM mapToEntity(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoORM(
                pedido.getId(),
                ClienteMapper.mapToEntity(pedido.getCliente()),
                pedido.getStatus(),
                ItemPedidoMapper.mapListaSimplesToEntity(pedido.getListItens())
        );
    }

    public static List<Pedido> mapListToEntity(List<PedidoORM> listEntity) {
        List<Pedido> list = new ArrayList<>();
        for (PedidoORM pedidoORM : listEntity) {
            list.add(Pedido.builder()
                    .id(pedidoORM.getId())
                    .cliente(ClienteMapper.mapToEntity(pedidoORM.getCliente()))
                    .status(pedidoORM.getStatus())
                    .listItens(ItemPedidoMapper.mapListToORM(pedidoORM.getListItens())).build()
            );
        }
        return list;
    }

}