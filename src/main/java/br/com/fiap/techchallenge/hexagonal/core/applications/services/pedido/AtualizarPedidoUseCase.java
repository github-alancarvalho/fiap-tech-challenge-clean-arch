package br.com.fiap.techchallenge.hexagonal.core.applications.services.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;

import java.util.Optional;


public class AtualizarPedidoUseCase {

    private PedidoGateway pedidoGateway;

    public AtualizarPedidoUseCase() {
        this.pedidoGateway = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido statusPedido) {
        return this.pedidoGateway.atualizarProgresso(pedido, statusPedido);
    }
}
