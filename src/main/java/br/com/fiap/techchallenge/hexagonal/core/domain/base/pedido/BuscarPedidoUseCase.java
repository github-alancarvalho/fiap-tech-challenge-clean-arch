package br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarPedidoUseCase {

    private PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return this.pedidoRepository.buscarPorId(id);
    }

    public Optional<List<Pedido>> buscarTodosPedidos() {
        return this.pedidoRepository.listarTudo();
    }

    public Optional<List<Pedido>> buscarPedidosPorStatus(StatusPedido statusPedido) {
        return this.pedidoRepository.listarPedidosPorStatus(statusPedido);
    }

    public Optional<List<Pedido>> buscarPedidosEmAberto() {
        return this.pedidoRepository.listarPedidosEmAberto();
    }
}
