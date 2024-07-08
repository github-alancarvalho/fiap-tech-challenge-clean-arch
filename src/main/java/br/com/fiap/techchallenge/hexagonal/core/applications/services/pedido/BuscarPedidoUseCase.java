package br.com.fiap.techchallenge.hexagonal.core.applications.services.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;

import java.util.List;
import java.util.Optional;


public class BuscarPedidoUseCase {

    private PedidoGateway pedidoGateway;

    public BuscarPedidoUseCase() {
        this.pedidoGateway = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return this.pedidoGateway.buscarPorId(id);
    }

    public Optional<List<Pedido>> buscarTodosPedidos() {
        return this.pedidoGateway.listarTudo();
    }

    public Optional<List<Pedido>> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return this.pedidoGateway.listarPedidosPorStatus(statusPedido);
    }

    public Optional<List<Pedido>> buscarPedidosEmAberto() {
        return this.pedidoGateway.listarPedidosEmAberto();
    }
}
