package br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;

import java.util.Optional;


public class InserirPedidoUseCase {

    private PedidoRepository pedidoRepository;

    public InserirPedidoUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> inserir(Pedido pedido) {
        return this.pedidoRepository.inserir(pedido);
    }
}
