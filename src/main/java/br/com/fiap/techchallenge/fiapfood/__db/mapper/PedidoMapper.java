package br.com.fiap.techchallenge.fiapfood.__db.mapper;

import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__db.PedidoEntity;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

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

    public static Pedido mapToEntity(PedidoResponse pedidoResponse) {
        if (pedidoResponse == null) {
            return null;
        }

        return new Pedido(
                pedidoResponse.getId(),
                pedidoResponse.getCliente(),
                pedidoResponse.getStatus(),
                pedidoResponse.getData(),
                pedidoResponse.getListItens()
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