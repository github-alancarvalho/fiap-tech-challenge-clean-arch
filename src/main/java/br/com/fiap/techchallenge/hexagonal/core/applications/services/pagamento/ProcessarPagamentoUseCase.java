package br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;

import java.util.List;
import java.util.Optional;


public class ProcessarPagamentoUseCase {

    private PagamentoGateway pagamentoGateway;

    public ProcessarPagamentoUseCase() {
        this.pagamentoGateway = DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> processarPagamento(Pagamento pagamento) {
        pagamento.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        return this.pagamentoGateway.processarPagamento(pagamento);
    }

    public Optional<List<Pagamento>> buscarTodosPagamentos() {
        return this.pagamentoGateway.listarPagamentos();
    }

}
