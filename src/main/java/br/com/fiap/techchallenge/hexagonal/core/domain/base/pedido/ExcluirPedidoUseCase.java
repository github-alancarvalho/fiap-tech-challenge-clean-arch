package br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;


public class ExcluirPedidoUseCase {

    private PedidoRepository pedidoRepository;

    public ExcluirPedidoUseCase() {
        this.pedidoRepository = DaoFactory.getInstance().getPedidoRepositoryORM();
    }

    public Boolean excluir(Pedido pedido) {
        return this.pedidoRepository.excluir(pedido);
    }


}
