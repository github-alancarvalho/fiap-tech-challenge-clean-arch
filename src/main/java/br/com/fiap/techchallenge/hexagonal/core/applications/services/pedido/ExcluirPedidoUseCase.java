package br.com.fiap.techchallenge.hexagonal.core.applications.services.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;


public class ExcluirPedidoUseCase {

    private PedidoGateway pedidoGateway;

    public ExcluirPedidoUseCase() {
        this.pedidoGateway = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Boolean excluir(Pedido pedido) {
        return this.pedidoGateway.excluir(pedido);
    }


}
