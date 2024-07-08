package br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;

import java.util.List;
import java.util.Optional;


public class BuscarPagamentoUseCase {

    private PagamentoGateway pagamentoGateway;

    public BuscarPagamentoUseCase() {
        this.pagamentoGateway = DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> buscarPagamentoPorId(Long id) {
        return this.pagamentoGateway.buscarPagamentoPorId(id);
    }

    public Optional<List<Pagamento>> buscarTodosPagamentos() {
        return this.pagamentoGateway.listarPagamentos();
    }

}
