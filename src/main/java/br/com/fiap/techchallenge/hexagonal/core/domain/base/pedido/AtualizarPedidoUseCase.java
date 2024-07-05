package br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;

import java.util.Optional;


public class AtualizarPedidoUseCase {

    private PedidoRepository pedidoRepository;

    public AtualizarPedidoUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido statusPedido) {
        return this.pedidoRepository.atualizarProgresso(pedido, statusPedido);
    }
}
