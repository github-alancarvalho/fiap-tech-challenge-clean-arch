package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.external.entities.PedidoEntity;

import java.util.ArrayList;
import java.util.List;

public class PedidoMapper {

    private PedidoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pedido mapToEntity(PedidoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Pedido(
                entity.getId(),
                ClienteMapper.mapToEntity(entity.getCliente()),
                entity.getStatus(),
                entity.getData(),
                ItemPedidoMapper.mapListToORM(entity.getListItens())
        );
    }

    public static PedidoEntity mapToEntity(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        return new PedidoEntity(
                pedido.getId(),
                ClienteMapper.mapToEntity(pedido.getCliente()),
                pedido.getStatus(),
                pedido.getData(),
                ItemPedidoMapper.mapListaSimplesToEntity(pedido.getListItens())
        );
    }

    public static List<Pedido> mapListToEntity(List<PedidoEntity> listEntity) {
        List<Pedido> list = new ArrayList<>();
        for (PedidoEntity pedidoEntity : listEntity) {
            list.add(Pedido.builder()
                    .id(pedidoEntity.getId())
                    .cliente(ClienteMapper.mapToEntity(pedidoEntity.getCliente()))
                            .data(pedidoEntity.getData())
                    .status(pedidoEntity.getStatus())
                    .listItens(ItemPedidoMapper.mapListToORM(pedidoEntity.getListItens())).build()
            );
        }
        return list;
    }

}