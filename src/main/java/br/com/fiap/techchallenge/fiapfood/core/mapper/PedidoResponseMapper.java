package br.com.fiap.techchallenge.fiapfood.core.mapper;

import br.com.fiap.techchallenge.fiapfood.dto.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;

public class PedidoResponseMapper {

    private PedidoResponseMapper() {
        throw new IllegalStateException("Utility class");
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

}