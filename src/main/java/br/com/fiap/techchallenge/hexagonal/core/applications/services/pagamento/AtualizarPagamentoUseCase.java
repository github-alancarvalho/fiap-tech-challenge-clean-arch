package br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;

import java.util.Optional;


public class AtualizarPagamentoUseCase {

    private PagamentoGateway pagamentoGateway;

    public AtualizarPagamentoUseCase() {
        this.pagamentoGateway =  DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> atualizarProgressoPagamento(Pagamento pagamento, StatusPagamento status) {
        return this.pagamentoGateway.atualizarStatusPagamento(pagamento, status);
    }

}
