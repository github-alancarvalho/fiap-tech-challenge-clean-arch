package br.com.fiap.techchallenge.hexagonal.core.applications.services.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;

import java.util.Optional;


public class InserirPedidoUseCase {

    private PedidoGateway pedidoGateway;

    public InserirPedidoUseCase() {
        this.pedidoGateway = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Optional<Pedido> inserir(Pedido pedido) {
        return this.pedidoGateway.inserir(pedido);
    }
}
